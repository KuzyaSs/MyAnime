package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.feature_anime_api.domain.repository.AnimeRepository

class AnimeDetailsViewModel(private val animeRepository: AnimeRepository) : ViewModel() {
    private val _state = MutableStateFlow(AnimeDetailsState())
    val state: StateFlow<AnimeDetailsState> = _state.asStateFlow()

    fun obtainEvent(event: AnimeDetailsEvent) {
        when (event) {
            is AnimeDetailsEvent.EnterAnimeDetailsScreen -> getAnime(animeId = event.animeId)
            AnimeDetailsEvent.Retry -> getAnime(_state.value.animeId)
        }
    }

    private fun getAnime(animeId: Int) {
        if (_state.value.anime != null) {
            return
        }
        _state.update {
            it.copy(
                animeId = animeId,
                isLoading = true,
                isError = false,
                error = null
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            when (val animeResult = animeRepository.getAnimeById(animeId = animeId)) {
                is Result.Success -> {
                    _state.update { it.copy(anime = animeResult.data, isLoading = false) }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            error = animeResult.error
                        )
                    }
                }
            }
        }
    }
}