package cache
import cache.errors.CacheError
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result

class CacheImpl<K, T>(
    private val size: String
) : Cache<K, T> {
    private val cache = mutableMapOf<K, T>()

    override fun get(key: K): Result<T, CacheError> {
        //logger.debug { "Obteniendo valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.getValue(key))
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    override fun put(key: K, value: T): Result<T, Nothing> {
        //logger.debug { "Guardando valor en la cache" }
        if (cache.size >= size.toInt() && !cache.containsKey(key)) {
            //logger.debug { "Eliminando valor de la cache" }
            cache.remove(cache.keys.first())
        }
        cache[key] = value
        return Ok(value)
    }

    override fun remove(key: K): Result<T, CacheError> {
        //logger.debug { "Eliminando valor de la cache" }
        return if (cache.containsKey(key)) {
            Ok(cache.remove(key)!!)
        } else {
            Err(CacheError("No existe el valor en la cache"))
        }
    }

    override fun clear(): Result<Unit, Nothing> {
        //logger.debug { "Limpiando cache" }
        cache.clear()
        return Ok(Unit)
    }
}