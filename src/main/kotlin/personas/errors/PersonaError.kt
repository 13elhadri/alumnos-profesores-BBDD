package personas.errors

sealed class PersonaError(val messaage:String) {
    class PersonaNoEncontrado(message: String) : PersonaError(message)
    class PersonaNoValido(message: String) : PersonaError(message)
    class PersonaNoActualizado(message: String) : PersonaError(message)
    class PersonaNoEliminado(message: String) : PersonaError(message)
}