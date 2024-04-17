package personas.dto

data class PersonaDto (
    val id:Long,
    val nombre:String,
    val createAt:String,
    val isDeleted:Boolean=false
)