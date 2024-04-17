package personas.service

import personas.models.Persona
import com.github.michaelbull.result.Result
import personas.errors.PersonaError


interface PersonasService {
    fun getAll(): Result<List<Persona>, PersonaError>
    fun getById(id: Long): Result<Persona, PersonaError>
    fun create(producto: Persona): Result<Persona, PersonaError>
    fun update(id: Long, producto: Persona): Result<Persona, PersonaError>
    fun delete(id: Long): Result<Persona, PersonaError>
}