package ru.ermakov.myanime.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ermakov.myanime.feature_anime_api.data.local.dao.AnimeDao
import ru.ermakov.myanime.feature_anime_api.data.local.model.LocalAnime

@Database(entities = [LocalAnime::class], version = 1)
abstract class MyAnimeDatabase : RoomDatabase() {
    abstract fun getAnimeDao(): AnimeDao
}