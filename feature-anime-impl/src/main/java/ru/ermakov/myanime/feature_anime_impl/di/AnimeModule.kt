package ru.ermakov.myanime.feature_anime_impl.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.ermakov.myanime.feature_anime_api.domain.repository.AnimeRepository
import ru.ermakov.myanime.feature_anime_impl.data.remote.api.AnimeApi
import ru.ermakov.myanime.feature_anime_impl.data.remote.data_source.AnimeRemoteDataSource
import ru.ermakov.myanime.feature_anime_impl.data.remote.data_source.AnimeRemoteDataSourceImpl
import ru.ermakov.myanime.feature_anime_impl.data.repository.AnimeRepositoryImpl

val animeModule = module {
    single<AnimeApi> {
        (get() as Retrofit).create(AnimeApi::class.java)
    }

    single<AnimeRemoteDataSource> {
        AnimeRemoteDataSourceImpl(get())
    }

    single<AnimeRepository> {
        AnimeRepositoryImpl(get())
    }
}