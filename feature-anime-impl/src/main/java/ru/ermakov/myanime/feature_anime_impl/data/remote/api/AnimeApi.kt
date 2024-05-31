package ru.ermakov.myanime.feature_anime_impl.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.ermakov.myanime.feature_anime_impl.data.remote.model.AnimeResponse

private const val LIMIT = 15

interface AnimeApi {
    @GET("anime")
    suspend fun getAnime(
        @Query("page") page: Int,
        @Query("limit") limit: Int = LIMIT,
        @Query("q") searchQuery: String
    ): Response<AnimeResponse>
}