import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import personas.models.Persona
import personas.service.PersonasService

class PersonasApp : KoinComponent {
   val contPorDefecto: PersonasService by inject()


    fun run() {
        contPorDefecto.create(
            Persona(
                id = 10,
                nombre = "yahya"
            )
        )

        val lista=contPorDefecto.getAll().value
        lista.forEach { println(it) }
    }
}