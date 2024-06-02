package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList

sealed interface AnimeListEvent {
    object LoadNextAnimeList : AnimeListEvent
    object Retry : AnimeListEvent
    object RefreshAnimeList : AnimeListEvent
    data class SearchAnime(val searchQuery: String) : AnimeListEvent
}
