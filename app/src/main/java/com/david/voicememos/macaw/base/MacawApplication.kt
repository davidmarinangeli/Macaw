package com.david.voicememos.macaw.base

import android.app.Application
import com.david.voicememos.macaw.ui.home.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MacawApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MacawApplication)
            modules(homeModule)
        }
    }
}