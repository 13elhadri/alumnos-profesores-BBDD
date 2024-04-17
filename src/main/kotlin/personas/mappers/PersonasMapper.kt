package personas.mappers

import database.PersonaEntity
import personas.dto.PersonaDto
import personas.models.Persona
import java.time.LocalDate
import java.time.LocalDateTime

fun PersonaEntity.toPersona(): Persona {
    return Persona(
        id = this.id,
        nombre = this.nombre,
        createAt = LocalDateTime.parse(this.created_at),
        isDeleted = this.is_deleted.toInt() == 1
    )
}

fun Persona.toPersonaDto(): PersonaDto {
    return PersonaDto(
        id = this.id,
        nombre = this.nombre,
        createAt = this.createAt.toString(),
        isDeleted = this.isDeleted
    )
}