package ru.ermakov.myanime.feature_anime_impl.data.remote.data_source

import ru.ermakov.myanime.core.domain.model.CoreError
import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime
import ru.ermakov.myanime.feature_anime_impl.data.mapper.toAnime
import ru.ermakov.myanime.feature_anime_impl.data.remote.api.AnimeApi

class AnimeRemoteDataSourceImpl(private val animeApi: AnimeApi) : AnimeRemoteDataSource {
    override suspend fun getAnimeList(
        page: Int,
        searchQuery: String
    ): Result<List<Anime>, RootError> {
        try {
            val animeListResponse = animeApi.getAnimeList(page = page, searchQuery = searchQuery)
            if (animeListResponse.isSuccessful) {
                animeListResponse.body()?.let { data ->
                    return Result.Success(
                        data = data.remoteAnimeList.map { remoteAnime ->
                            remoteAnime.toAnime()
                        }
                    )
                }
            }
            return Result.Error(error = CoreError.CONNECTION_FAILURE)
        } catch (exception: Exception) {
            return Result.Error(error = CoreError.CONNECTION_FAILURE)
        }
    }

    override suspend fun getAnimeById(animeId: Int): Result<Anime, RootError> {
        try {
            val animeResponse = animeApi.getAnimeById(animeId = animeId)
            if (animeResponse.isSuccessful) {
                animeResponse.body()?.let { data ->
                    return Result.Success(data = data.remoteAnime.toAnime())
                }
            }
            return Result.Error(error = CoreError.CONNECTION_FAILURE)
        } catch (exception: Exception) {
            return Result.Error(error = CoreError.CONNECTION_FAILURE)
        }
    }
}