package com.urovo.develop

import android.app.Application
import com.urovo.core.config.Configurator

/**
 * create by 大白菜
 * description
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Configurator.getInstance()
            .writeHost("www.baidu.com")
            .writeTimeout(10L)
            .configure()
    }
}