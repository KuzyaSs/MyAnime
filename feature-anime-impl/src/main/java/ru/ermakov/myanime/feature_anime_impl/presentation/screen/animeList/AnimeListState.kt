package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList

import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

internal const val DEFAULT_ANIME_PAGE = 0

data class AnimeListState(
    val animeList: List<Anime> = emptyList(),
    val searchQuery: String = "",
    val animePage: Int = DEFAULT_ANIME_PAGE,
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: RootError? = null
)
