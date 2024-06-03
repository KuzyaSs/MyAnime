package ru.ermakov.myanime.di

import org.koin.dsl.module
import ru.ermakov.myanime.core.di.coreModule
import ru.ermakov.myanime.database.di.databaseModule
import ru.ermakov.myanime.feature_anime_impl.di.animeModule

val appModule = module {
    includes(coreModule, databaseModule, animeModule)
}