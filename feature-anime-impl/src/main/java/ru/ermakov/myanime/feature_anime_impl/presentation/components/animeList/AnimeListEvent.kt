package ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList

sealed interface AnimeListEvent {
    object LoadNextAnime : AnimeListEvent
    object RefreshAnime : AnimeListEvent
    data class SearchAnime(val searchQuery: String) : AnimeListEvent
}
