package di

import cache.Cache
import cache.CacheImpl
import database.service.SqlDeLightManager
import org.koin.dsl.module
import personas.models.Persona
import personas.repositories.PersonasRepository
import personas.repositories.PersonasRepositoryImpl
import personas.service.PersonasService
import personas.service.PersonasServiceImpl


val personasModule= module {
    factory<Cache<Long, Persona>> { CacheImpl(getProperty("cache.size")) }

    single<SqlDeLightManager >{
        SqlDeLightManager(
            getProperty("database.url"),
            getProperty("database.inmemory"),
            getProperty("database.init.data")
        )
    }

    single<PersonasRepository> { PersonasRepositoryImpl(get()) }



    factory<PersonasService> { PersonasServiceImpl(get(),get()) }


}

/*
object PersonasModule {
    fun getPersonaRepository(): PersonasRepository {
        return PersonasRepositoryImpl(getSqlDeligManager())
    }
    fun getCache(): Cache<Long,Persona> {
        return CacheImpl(
            size = getConfig().cacheSize
        )
    }
    fun getPersonasService(): PersonasService {
        return PersonasServiceImpl(
            getPersonaRepository(),

            )
    }
    /* fun getProductosStorage(): ProductosStorage {
         return ProductosStorageImpl()
     }

     */


    fun getConfig(): Config {
        return Config
    }

    fun getSqlDeligManager(): SqlDeLightManager {
        return SqlDeLightManager(
            databaseUrl = getConfig().databaseUrl,
            databaseInMemory = getConfig().databaseInMemory,
            databaseInitData = getConfig().databaseInitData
        )
    }


}

 */

