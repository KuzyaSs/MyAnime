package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.ermakov.myanime.core.domain.model.Result
import ru.ermakov.myanime.feature_anime_api.domain.repository.AnimeRepository

private const val GET_ANIME_DELAY = 1500L

class AnimeListViewModel(private val animeRepository: AnimeRepository) : ViewModel() {
    private val _state = MutableStateFlow(AnimeListState())
    var state: StateFlow<AnimeListState> = _state.asStateFlow()

    private var getAnimeJob: Job? = null

    init {
        getAnimeList()
    }

    fun obtainEvent(event: AnimeListEvent) {
        when (event) {
            is AnimeListEvent.LoadNextAnimeList -> getAnimeList()
            is AnimeListEvent.Retry -> getAnimeList()
            is AnimeListEvent.RefreshAnimeList -> refreshAnimeList()
            is AnimeListEvent.SearchAnime -> searchAnime(searchQuery = event.searchQuery)
        }
    }

    private fun getAnimeList() {
        if (_state.value.isLoading) {
            return
        }
        getAnimeJob?.cancel()

        _state.update { it.copy(isLoading = true, isError = false, error = null) }
        getAnimeJob = viewModelScope.launch(Dispatchers.IO) {
            delay(GET_ANIME_DELAY)
            val animeResult = animeRepository.getAnimeList(
                page = _state.value.animePage.inc(),
                searchQuery = _state.value.searchQuery
            )
            when (animeResult) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            animeList = it.animeList + animeResult.data,
                            animePage = if (animeResult.data.isNotEmpty()) it.animePage.inc() else it.animePage,
                            isRefreshing = false,
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isRefreshing = false,
                            isLoading = false,
                            isError = true,
                            error = animeResult.error
                        )
                    }
                }
            }
        }
    }

    private fun refreshAnimeList() {
        _state.update {
            it.copy(
                animeList = emptyList(),
                animePage = DEFAULT_ANIME_PAGE,
                isRefreshing = true
            )
        }
        getAnimeList()
    }

    private fun searchAnime(searchQuery: String) {
        _state.update {
            it.copy(
                animeList = emptyList(),
                searchQuery = searchQuery,
                animePage = DEFAULT_ANIME_PAGE
            )
        }
        getAnimeList()
    }
}