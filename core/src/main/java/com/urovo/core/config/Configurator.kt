package com.urovo.core.config

import android.app.Activity
import android.os.Handler
import okhttp3.Interceptor
import java.util.*
import kotlin.collections.ArrayList

/**
 * create by 大白菜
 * description
 */
class Configurator {

    companion object{
        private val NORMAL_CONFIGS = WeakHashMap<Any, Any>()
        private val INTERCEPTORS = ArrayList<Interceptor>()
        private val HANDLER = Handler()

        private class Holder {

            companion object{
                val INSTANCE = Configurator()
            }
        }

        fun getInstance() = Holder.INSTANCE

    }

    internal fun getNormalConfigs(): WeakHashMap<Any, Any> {
        return NORMAL_CONFIGS
    }

    fun configure(){
        NORMAL_CONFIGS[ConfigKey.CONFIG_READY] = true
        NORMAL_CONFIGS[ConfigKey.HANDLER] =
            HANDLER
    }

    fun writeHost(host: String): Configurator {
        NORMAL_CONFIGS[ConfigKey.API_HOST] = host
        return this
    }

    fun writeInterceptor(interceptor: Interceptor): Configurator {
        INTERCEPTORS.add(interceptor)
        return this
    }

    fun withDBPath(dbPath: String): Configurator {
        NORMAL_CONFIGS[ConfigKey.GREENDAO_PATH] = dbPath
        return this
    }

    fun withActivity(activity: Activity): Configurator {
        NORMAL_CONFIGS[ConfigKey.ACTIVITY] = activity
        return this
    }

    fun writeTimeout(timeout: Long): Configurator {
        NORMAL_CONFIGS[ConfigKey.TIME_OUT] = timeout
        return this
    }

    //检查配置是否完成
    private fun checkConfiguration() {
        val isReady = NORMAL_CONFIGS[ConfigKey.CONFIG_READY] as Boolean
        if (!isReady) {
            throw RuntimeException("Configuration is not ready, call configure")
        }
    }

    fun <T> getConfiguration(key: Any): T {
        checkConfiguration()
        val value = NORMAL_CONFIGS[key] ?: throw NullPointerException(key.toString() + "IS NULL")
        return value as T
    }

}