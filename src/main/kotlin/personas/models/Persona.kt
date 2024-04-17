package personas.models

import java.time.LocalDate
import java.time.LocalDateTime

class Persona(
    val id:Long,
    var nombre:String,
    val createAt: LocalDateTime=LocalDateTime.now(),
    var isDeleted:Boolean=false
){
    override fun toString(): String {
        return "$id - $nombre"
    }
}