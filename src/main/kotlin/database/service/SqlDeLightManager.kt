package database.service

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import database.data.initDemoPersonas
import org.example.database.AppDatabase
import org.lighthousegames.logging.logging

private val logger = logging()

class SqlDeLightManager(
    private val databaseUrl: String,
    private val databaseInMemory: String,
    private val databaseInitData: String,
) {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        //logger.debug { "Inicializando el gestor de Bases de Datos con SQLDelight" }
        // Inicializamos datos de ejemplo, si se ha configurado
        initialize()
    }

    private fun initQueries(): DatabaseQueries {

        return if (databaseInMemory.toBoolean()) {
            //logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            //logger.debug { "SqlDeLightClient - File: ${databaseUrl}" }
            JdbcSqliteDriver(databaseUrl)
        }.let { driver ->
            // Creamos la base de datos
            //logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries

    }

    fun initialize() {
        if (databaseInitData.toBoolean()) {
            removeAllData()
            initDataExamples()
        }
    }

    private fun initDataExamples() {
        //logger.debug { "Iniciando datos de ejemplo" }
        databaseQueries.transaction {
            demoClientes()
        }
    }



    private fun demoClientes() {
        //logger.debug { "Datos de ejemplo de Persona" }
        initDemoPersonas().forEach {
            databaseQueries.insertPersona(
                nombre = it.nombre,
                created_at = it.createAt.toString(),
            )
        }
    }


    // limpiamos las tablas
    private fun removeAllData() {
       // logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.removeAllPersonas()
        }
    }
}