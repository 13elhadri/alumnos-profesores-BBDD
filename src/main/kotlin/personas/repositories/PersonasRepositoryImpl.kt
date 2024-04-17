package personas.repositories

import database.service.SqlDeLightManager
import personas.mappers.toPersona
import personas.models.Persona
import java.time.LocalDateTime

class PersonasRepositoryImpl (
    private val dbManager: SqlDeLightManager
) : PersonasRepository {
    private val db = dbManager.databaseQueries

    override fun findAll(): List<Persona> {
        //logger.debug { "Buscando todos los clientes" }
        return db.selectAllPersonas().executeAsList().map { it.toPersona() }
    }

    override fun findById(id: Long): Persona? {
        //logger.debug { "Buscando cliente por id: $id" }
        return db.selectPersonaById(id).executeAsOneOrNull()?.toPersona()
    }

    override fun save(cliente: Persona): Persona {
        //logger.debug { "Guardando cliente: $cliente" }

        val timeStamp = LocalDateTime.now().toString()

        db.transaction {
            db.insertPersona(
                nombre = cliente.nombre,
                created_at = timeStamp,
            )
        }

        return db.selectPersonaLastInserted().executeAsOne().toPersona()
    }

    override fun update(id: Long, cliente: Persona): Persona? {
        //logger.debug { "Actualizando cliente por id: $id" }
        var result = this.findById(id) ?: return null
        val timeStamp = LocalDateTime.now()
        result.nombre=cliente.nombre
        result.isDeleted=cliente.isDeleted


        db.updatePersona(
            nombre = result.nombre,
            is_deleted = if (result.isDeleted) 1 else 0,
            id = id,
        )
        return result
    }

    override fun delete(id: Long): Persona? {
        //logger.debug { "Borrando cliente por id: $id" }
        val result = this.findById(id) ?: return null
        // Esto es borrado lógico
        val timeStamp = LocalDateTime.now()
        db.updatePersona(
            nombre = result.nombre,
            is_deleted = 1,
            id = result.id,
        )
        result.isDeleted=true

        return result
    }

}