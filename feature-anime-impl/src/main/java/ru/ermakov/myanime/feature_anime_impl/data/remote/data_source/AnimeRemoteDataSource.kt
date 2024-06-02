package ru.ermakov.myanime.feature_anime_impl.data.remote.data_source

import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

interface AnimeRemoteDataSource {
    suspend fun getAnimeList(page: Int, searchQuery: String): Result<List<Anime>, RootError>
    suspend fun getAnimeById(animeId: Int): Result<Anime, RootError>
}