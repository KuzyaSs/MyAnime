package ru.ermakov.myanime.feature_anime_api.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class LocalAnime(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "episodes")
    val episodes: Int,
    @ColumnInfo(name = "score")
    val score: Double,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "popularity")
    val popularity: Int,
    @ColumnInfo(name = "members")
    val members: Int,
    @ColumnInfo(name = "favorites")
    val favorites: Int,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "background")
    val background: String,
    @ColumnInfo(name = "year")
    val year: Int
)
