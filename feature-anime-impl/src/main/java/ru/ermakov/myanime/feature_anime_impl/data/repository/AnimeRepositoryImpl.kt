package ru.ermakov.myanime.feature_anime_impl.data.repository

import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime
import ru.ermakov.myanime.feature_anime_api.domain.repository.AnimeRepository
import ru.ermakov.myanime.feature_anime_impl.data.remote.data_source.AnimeRemoteDataSource

class AnimeRepositoryImpl(private val animeRemoteDataSource: AnimeRemoteDataSource) : AnimeRepository {
    override suspend fun getAnimeList(page: Int, searchQuery: String): Result<List<Anime>, RootError> {
        return animeRemoteDataSource.getAnimeList(page = page, searchQuery = searchQuery)
    }

    override suspend fun getAnimeById(animeId: Int): Result<Anime, RootError> {
        return animeRemoteDataSource.getAnimeById(animeId = animeId)
    }
}