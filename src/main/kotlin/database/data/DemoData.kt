package database.data

import personas.models.Persona
import java.time.LocalDate
import java.time.LocalDateTime

fun initDemoPersonas() = listOf(
    Persona(
        id = 1,
        nombre = "Juan Pérez",
        createAt = LocalDateTime.now()
    ),
    Persona(
        id = 2,
        nombre = "Jose Maria",
        createAt = LocalDateTime.now()
    ),
    Persona(
        id = 3,
        nombre = "Luis Garcia",
        createAt = LocalDateTime.now()
    ),
    Persona(
        id = 4,
        nombre = "Jorge Fernandez",
        createAt = LocalDateTime.now()
    )
)