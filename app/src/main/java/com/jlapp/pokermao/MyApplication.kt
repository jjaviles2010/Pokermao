package com.jlapp.pokermao

import android.app.Application
import com.facebook.stetho.Stetho
import com.jlapp.pokermao.di.networkModule
import com.jlapp.pokermao.di.repositoryModule
import com.jlapp.pokermao.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}