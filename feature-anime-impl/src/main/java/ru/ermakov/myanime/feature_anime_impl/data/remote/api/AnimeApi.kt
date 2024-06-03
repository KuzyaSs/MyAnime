package ru.ermakov.myanime.feature_anime_impl.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.ermakov.myanime.feature_anime_api.data.local.dao.LIMIT_ANIME_PER_PAGE
import ru.ermakov.myanime.feature_anime_impl.data.remote.model.AnimeListResponse
import ru.ermakov.myanime.feature_anime_impl.data.remote.model.AnimeResponse

interface AnimeApi {
    @GET("anime")
    suspend fun getAnimeList(
        @Query("page") page: Int,
        @Query("limit") limit: Int = LIMIT_ANIME_PER_PAGE,
        @Query("q") searchQuery: String
    ): Response<AnimeListResponse>

    @GET("anime/{animeId}")
    suspend fun getAnimeById(@Path("animeId") animeId: Int): Response<AnimeResponse>
}