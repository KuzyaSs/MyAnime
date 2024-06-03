package ru.ermakov.myanime.feature_anime_impl.data.local.data_source

import ru.ermakov.myanime.core.domain.model.CoreError
import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.data.local.dao.AnimeDao
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime
import ru.ermakov.myanime.feature_anime_api.domain.model.AnimeError
import ru.ermakov.myanime.feature_anime_impl.data.mapper.toAnime
import ru.ermakov.myanime.feature_anime_impl.data.mapper.toLocalAnime

class AnimeLocalDataSourceImpl(private val animeDao: AnimeDao) : AnimeLocalDataSource {
    override suspend fun insertAnimeList(animeList: List<Anime>): Result<Unit, RootError> {
        return try {
            animeDao.insertAnime(localAnimeList = animeList.map { anime -> anime.toLocalAnime() })
            Result.Success(data = Unit)
        } catch (exception: Exception) {
            Result.Error(error = CoreError.DATABASE_ERROR)
        }
    }

    override suspend fun getAnimeList(
        page: Int,
        searchQuery: String
    ): Result<List<Anime>, RootError> {
        return try {
            val localAnimeList = animeDao.getAnimeList(page = page, searchQuery = searchQuery)
            return Result.Success(data = localAnimeList.map { localAnime -> localAnime.toAnime() })
        } catch (exception: Exception) {
            Result.Error(error = CoreError.DATABASE_ERROR)
        }
    }

    override suspend fun getAnimeById(animeId: Int): Result<Anime, RootError> {
        return try {
            val localAnime = animeDao.getAnimeById(animeId = animeId)
            if (localAnime != null) {
                Result.Success(data = localAnime.toAnime())
            } else {
                Result.Error(error = AnimeError.LOCAL_ANIME_NOT_FOUND)
            }
        } catch (exception: Exception) {
            Result.Error(error = CoreError.DATABASE_ERROR)
        }
    }
}