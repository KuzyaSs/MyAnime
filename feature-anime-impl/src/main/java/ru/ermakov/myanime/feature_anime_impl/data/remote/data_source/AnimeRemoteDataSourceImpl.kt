package ru.ermakov.myanime.feature_anime_impl.data.remote.data_source

import ru.ermakov.myanime.core.domain.model.CoreError
import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_impl.data.mapper.toAnime
import ru.ermakov.myanime.feature_anime_impl.data.remote.api.AnimeApi
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

class AnimeRemoteDataSourceImpl(private val animeApi: AnimeApi) : AnimeRemoteDataSource {
    override suspend fun getAnime(page: Int, searchQuery: String): Result<List<Anime>, RootError> {
        val animeResponse = animeApi.getAnime(page = page, searchQuery = searchQuery)
        if (animeResponse.isSuccessful) {
            animeResponse.body()?.let { data ->
                return Result.Success(
                    data = data.remoteAnimeList.map { remoteAnime ->
                        remoteAnime.toAnime()
                    }
                )
            }
        }
        return Result.Error(error = CoreError.CONNECTION_FAILURE)
    }
}