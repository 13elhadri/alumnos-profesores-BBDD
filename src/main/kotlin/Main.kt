
import di.personasModule
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties
import org.koin.test.verify.Verify.verify
import personas.models.Persona
import org.koin.test.verify.verify

@OptIn(KoinExperimentalAPI::class)
fun main() {
    println("Hola Koin")
    // Inicializamos Koin
    startKoin {
        // declare used logger
        //printLogger()
        // Leemos las propiedades de un fichero
        fileProperties("/config.properties") // Por defecto busca en src/main/resources/koin.properties, pero puede ser otro fichero si se lo pasas como parametro
        // declara modulos de inyección de dependencias, pero lo verificamos antes de inyectarlos
        personasModule.verify(extraTypes = listOf(kotlin.Boolean::class))
        modules(personasModule)
    }



    val app = PersonasApp()
    app.run()



}