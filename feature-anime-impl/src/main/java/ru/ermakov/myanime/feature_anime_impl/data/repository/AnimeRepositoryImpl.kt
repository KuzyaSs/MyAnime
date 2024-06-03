package ru.ermakov.myanime.feature_anime_impl.data.repository

import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime
import ru.ermakov.myanime.feature_anime_api.domain.repository.AnimeRepository
import ru.ermakov.myanime.feature_anime_impl.data.local.data_source.AnimeLocalDataSource
import ru.ermakov.myanime.feature_anime_impl.data.mapper.toLocalAnime
import ru.ermakov.myanime.feature_anime_impl.data.remote.data_source.AnimeRemoteDataSource

class AnimeRepositoryImpl(
    private val animeLocalDataSource: AnimeLocalDataSource,
    private val animeRemoteDataSource: AnimeRemoteDataSource
) : AnimeRepository {
    override suspend fun getAnimeList(
        page: Int,
        searchQuery: String
    ): Result<List<Anime>, RootError> {
        val animeListResult = animeRemoteDataSource.getAnimeList(
            page = page,
            searchQuery = searchQuery
        )
        return when (animeListResult) {
            is Result.Success -> {
                animeLocalDataSource.insertAnimeList(animeList = animeListResult.data)
                animeListResult
            }

            is Result.Error -> animeLocalDataSource.getAnimeList(
                page = page,
                searchQuery = searchQuery
            )
        }
    }

    override suspend fun getAnimeById(animeId: Int): Result<Anime, RootError> {
        return when (val animeResult = animeRemoteDataSource.getAnimeById(animeId = animeId)) {
            is Result.Success -> {
                animeLocalDataSource.insertAnimeList(animeList = listOf(animeResult.data))
                animeResult
            }

            is Result.Error -> animeLocalDataSource.getAnimeById(animeId = animeId)
        }
    }
}