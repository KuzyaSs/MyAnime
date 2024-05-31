package ru.ermakov.myanime.feature_anime_api.domain.repository

import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

interface AnimeRepository {
    suspend fun getAnime(page: Int, searchQuery: String): Result<List<Anime>, RootError>
}