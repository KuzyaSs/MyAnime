package ru.ermakov.myanime.feature_anime_api.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.ermakov.myanime.feature_anime_api.data.local.model.LocalAnime

const val LIMIT_ANIME_PER_PAGE = 15

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(localAnimeList: List<LocalAnime>)

    @Query("SELECT * FROM anime WHERE title LIKE '%' || :searchQuery || '%' LIMIT :limit OFFSET (:page - 1) * :limit")
    suspend fun getAnimeList(
        page: Int,
        limit: Int = LIMIT_ANIME_PER_PAGE,
        searchQuery: String
    ): List<LocalAnime>

    @Query("SELECT * FROM anime WHERE id = :animeId")
    suspend fun getAnimeById(animeId: Int): LocalAnime?
}