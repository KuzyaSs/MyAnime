package ru.ermakov.myanime.database.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.ermakov.myanime.database.data.MyAnimeDatabase

val databaseModule = module {
    single<MyAnimeDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = MyAnimeDatabase::class.java,
            name = MyAnimeDatabase::class.simpleName
        ).build()
    }
}