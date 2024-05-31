package ru.ermakov.myanime.feature_anime_impl.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteAnime(
    @SerializedName("mal_id")
    val id: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("images")
    val image: RemoteImage,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("episodes")
    val episodes: Int,
    @SerializedName("score")
    val score: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("favorites")
    val favorites: Int,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("background")
    val background: String,
    @SerializedName("year")
    val year: Int
)