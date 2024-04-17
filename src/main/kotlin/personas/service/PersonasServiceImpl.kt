package personas.service

import personas.models.Persona
import personas.repositories.PersonasRepository
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import personas.errors.PersonaError

class PersonasServiceImpl (
    private val personasRepository: PersonasRepository
) : PersonasService {
    override fun getAll(): Result<List<Persona>, PersonaError> {
        //logger.debug { "Obteniendo todos los productos" }
        return Ok(personasRepository.findAll())
    }


    override fun getById(id: Long): Result<Persona, PersonaError> {
        //logger.debug { "Obteniendo producto por id: $id" }
        return personasRepository.findById(id)
            ?.let { Ok(it) }
            ?: Err(PersonaError.PersonaNoEncontrado("Producto no encontrado con id: $id"))
    }

    override fun create(producto: Persona): Result<Persona, PersonaError> {
        //logger.debug { "Guardando producto: $producto" }
        return Ok(personasRepository.save(producto))
    }

    override fun update(id: Long, producto: Persona): Result<Persona, PersonaError> {
       // logger.debug { "Actualizando producto por id: $id" }
        return personasRepository.update(id, producto)
            ?.let { Ok(it) }
            ?: Err(PersonaError.PersonaNoActualizado("Producto no actualizado con id: $id"))
    }

    override fun delete(id: Long): Result<Persona, PersonaError> {
        //logger.debug { "Borrando producto por id: $id" }
        return personasRepository.delete(id)
            ?.let { Ok(it) }
            ?: Err(PersonaError.PersonaNoEliminado("Producto no eliminado con id: $id"))
    }

}