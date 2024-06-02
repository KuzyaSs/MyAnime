package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime
import ru.ermakov.myanime.feature_anime_api.presentation.toStringAnimeError
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails.navigateToAnimeDetailsDestination
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.component.AnimeList
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.component.AnimeSearchTextField
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.component.EmptyAnimeListScreen
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.component.ErrorAnimeListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    state: AnimeListState,
    onEvent: (AnimeListEvent) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullToRefreshState()
    Box(modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection)) {
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        if (pullToRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                onEvent(AnimeListEvent.RefreshAnimeList)
            }
        }
        LaunchedEffect(state.isRefreshing) {
            if (state.isRefreshing) {
                pullToRefreshState.startRefresh()
            } else {
                pullToRefreshState.endRefresh()
            }
        }

        Column(modifier = modifier.fillMaxSize()) {
            AnimeSearchTextField(
                searchQuery = state.searchQuery,
                onEvent = { event -> onEvent(event) },
            )
            when {
                (state.isError && state.error != null && state.animeList.isEmpty()) -> {
                    ErrorAnimeListScreen(
                        errorMessage = state.error.toStringAnimeError(context = LocalContext.current),
                        onEvent = { event -> onEvent(event) }
                    )
                }

                else -> {
                    if (state.animeList.isEmpty() && !state.isLoading) {
                        EmptyAnimeListScreen(onEvent = { event -> onEvent(event) })
                    } else {
                        AnimeList(
                            animeList = state.animeList,
                            isLoading = state.isLoading,
                            loadNextAnime = { onEvent(AnimeListEvent.LoadNextAnimeList) },
                            onItemClicked = { id ->
                                navController.navigateToAnimeDetailsDestination(animeId = id)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview() {
    MyAnimeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AnimeListScreen(
                state = AnimeListState(
                    animeList = listOf(
                        Anime(
                            id = 1,
                            url = "https://myanimelist.net/anime/19/Monster",
                            image = "https://cdn.myanimelist.net/images/anime/10/18793.jpg",
                            title = "Monster",
                            type = "TV",
                            episodes = 1,
                            score = 5.0,
                            rank = 1,
                            popularity = 1,
                            members = 1,
                            favorites = 1,
                            synopsis = "Dr. Kenzou Tenma, an elite neurosurgeon recently engaged to his hospital director's daughter, is well on his way to ascending the hospital hierarchy. That is until one night, a seemingly small event changes Dr. Tenma's life forever. While preparing to perform surgery on someone, he gets a call from the hospital director telling him to switch patients and instead perform life-saving brain surgery on a famous performer. His fellow doctors, fiancée, and the hospital director applaud his accomplishment; but because of the switch, a poor immigrant worker is dead, causing Dr. Tenma to have a crisis of conscience.\\n\\nSo when a similar situation arises, Dr. Tenma stands his ground and chooses to perform surgery on the young boy Johan Liebert instead of the town's mayor. Unfortunately, this choice leads to serious ramifications for Dr. Tenma—losing his social standing being one of them. However, with the mysterious death of the director and two other doctors, Dr. Tenma's position is restored. With no evidence to convict him, he is released and goes on to attain the position of hospital director. \\n\\nNine years later when Dr. Tenma saves the life of a criminal, his past comes back to haunt him—once again, he comes face to face with the monster he operated on. He must now embark on a quest of pursuit to make amends for the havoc spread by the one he saved.\\n\\n[Written by MAL Rewrite]",
                            background = "",
                            year = 2004
                        )
                    )
                ),
                onEvent = {},
                navController = rememberNavController()
            )
        }
    }
}