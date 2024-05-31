package ru.ermakov.myanime.app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.ermakov.myanime.di.appModule

class MyAnimeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyAnimeApplication)
            modules(appModule)
        }
    }
}