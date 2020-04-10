package com.urovo.core.config

import android.content.Context
import android.os.Handler

/**
 * create by 大白菜
 * description
 */
class Normal {

    companion object {
        fun init(context: Context): Configurator {
            Configurator.getInstance()
                .getNormalConfigs()
                .put(ConfigKey.APPLICATION_CONTEXT, context.applicationContext)
            return Configurator.getInstance()
        }

        fun getConfigurator(): Configurator {
            return Configurator.getInstance()
        }

        fun <T> getConfiguration(key: Any): T {
            return getConfigurator().getConfiguration(key)
        }

        fun getApplication(): Context {
            return getConfiguration(ConfigKey.APPLICATION_CONTEXT)
        }

        fun getHandler(): Handler {
            return getConfiguration(ConfigKey.HANDLER)
        }
    }
}