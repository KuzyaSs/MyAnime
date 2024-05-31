package ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList

import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

data class AnimeListState(
    val animeList: List<Anime> = emptyList(),
    val searchQuery: String = "",
    val isSearchMode: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: RootError? = null
)
