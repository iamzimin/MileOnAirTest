package com.evg.mileonairtest

import android.app.Application
import com.evg.settings.di.settingsModule
import com.evg.shared_prefs.di.sharedPrefsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(settingsModule, sharedPrefsModule)
        }
    }
}