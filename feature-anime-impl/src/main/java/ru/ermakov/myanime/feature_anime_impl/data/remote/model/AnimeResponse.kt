package ru.ermakov.myanime.feature_anime_impl.data.remote.model

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val remoteAnime: RemoteAnime,
)