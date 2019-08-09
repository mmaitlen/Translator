package com.mgm.translator

import android.app.Application
import com.mgm.translate.di.AppComponent
import com.mgm.translate.di.AppModule
import com.mgm.translate.di.DaggerAppComponent

class TranslatorApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
}