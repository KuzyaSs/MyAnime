package ru.ermakov.myanime.feature_anime_api.domain.model

data class Anime(
    val id: Int,
    val url: String,
    val image: String,
    val title: String,
    val type: String,
    val episodes: Int,
    val score: Double, //
    val rank: Int, //
    val popularity: Int, //
    val members: Int, //
    val favorites: Int,
    val synopsis: String,
    val background: String,
    val year: Int
)
