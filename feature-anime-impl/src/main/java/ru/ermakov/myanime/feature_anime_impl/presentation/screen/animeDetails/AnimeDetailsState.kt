package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails

import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

internal const val INVALID_ANIME_ID = -1

data class AnimeDetailsState(
    val animeId: Int = INVALID_ANIME_ID,
    val anime: Anime? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: RootError? = null,
)