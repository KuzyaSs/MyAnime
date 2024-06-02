package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails

sealed interface AnimeDetailsEvent {
    data class EnterAnimeDetailsScreen(val animeId: Int) : AnimeDetailsEvent
    object Retry : AnimeDetailsEvent
}