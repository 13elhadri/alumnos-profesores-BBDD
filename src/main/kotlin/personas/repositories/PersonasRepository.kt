package personas.repositories

import personas.models.Persona

interface PersonasRepository {
    fun findAll(): List<Persona>
    fun findById(id: Long): Persona?
    fun save(cliente: Persona): Persona
    fun update(id: Long, cliente: Persona): Persona?
    fun delete(id: Long): Persona?
}