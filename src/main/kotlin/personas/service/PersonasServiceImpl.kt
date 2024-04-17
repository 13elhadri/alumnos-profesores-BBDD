package personas.service

import cache.Cache
import com.github.michaelbull.result.*
import personas.models.Persona
import personas.repositories.PersonasRepository
import personas.errors.PersonaError

class PersonasServiceImpl (
    private val personasRepository: PersonasRepository,
    private val cache: Cache<Long,Persona>
) : PersonasService {
    override fun getAll(): Result<List<Persona>, PersonaError> {
        //logger.debug { "Obteniendo todos los Personas" }
        return Ok(personasRepository.findAll())
    }


    override fun getById(id: Long): Result<Persona, PersonaError> {
        //logger.debug { "Obteniendo Persona por id: $id" }

        return cache.get(id).mapBoth(
            success = {
                //logger.debug { "Persona encontrado en cache" }
                Ok(it)
            },
            failure = {
                //logger.debug { "Persona no encontrado en cache" }
                personasRepository.findById(id)
                    ?.let { Ok(it) }
                    ?: Err(PersonaError.PersonaNoEncontrado("persona no encontrado con id: $id"))
            }
        )
    }

    override fun create(persona: Persona): Result<Persona, PersonaError> {
        //logger.debug { "Guardando Persona: $persona" }
        return Ok( personasRepository.save(persona))
        .also {
            println("Guardando en cache")
            cache.put(persona.id, persona)
        }
    }

    override fun update(id: Long, persona: Persona): Result<Persona, PersonaError> {

        return personasRepository.update(id, persona)
                ?.let { Ok(it) }
                ?: Err(PersonaError.PersonaNoActualizado("Persona no actualizado con id: $id"))
                    .andThen {
                        cache.put(id,persona)
                    }
    }

    override fun delete(id: Long): Result<Persona, PersonaError> {
        //logger.debug { "Borrando Persona por id: $id" }
        return personasRepository.delete(id)
            ?.let {
                cache.remove(id)
                Ok(it)
            }
            ?: Err(PersonaError.PersonaNoEliminado("Persona no eliminado con id: $id"))
    }

}