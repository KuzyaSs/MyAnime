package ru.ermakov.myanime.feature_anime_impl.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.ermakov.myanime.database.data.MyAnimeDatabase
import ru.ermakov.myanime.feature_anime_api.data.local.dao.AnimeDao
import ru.ermakov.myanime.feature_anime_api.domain.repository.AnimeRepository
import ru.ermakov.myanime.feature_anime_impl.data.local.data_source.AnimeLocalDataSource
import ru.ermakov.myanime.feature_anime_impl.data.local.data_source.AnimeLocalDataSourceImpl
import ru.ermakov.myanime.feature_anime_impl.data.remote.api.AnimeApi
import ru.ermakov.myanime.feature_anime_impl.data.remote.data_source.AnimeRemoteDataSource
import ru.ermakov.myanime.feature_anime_impl.data.remote.data_source.AnimeRemoteDataSourceImpl
import ru.ermakov.myanime.feature_anime_impl.data.repository.AnimeRepositoryImpl
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails.AnimeDetailsViewModel
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.AnimeListViewModel

val animeModule = module {
    single<AnimeApi> {
        (get() as Retrofit).create(AnimeApi::class.java)
    }

    single<AnimeRemoteDataSource> {
        AnimeRemoteDataSourceImpl(get())
    }

    single<AnimeDao> {
        (get() as MyAnimeDatabase).getAnimeDao()
    }

    single<AnimeLocalDataSource> {
        AnimeLocalDataSourceImpl(get())
    }

    single<AnimeRepository> {
        AnimeRepositoryImpl(get(), get())
    }

    viewModel<AnimeListViewModel> {
        AnimeListViewModel(get())
    }

    viewModel<AnimeDetailsViewModel> {
        AnimeDetailsViewModel(get())
    }
}