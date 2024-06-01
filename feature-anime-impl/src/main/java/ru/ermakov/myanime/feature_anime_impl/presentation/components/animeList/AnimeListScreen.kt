package ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.core.presentation.theme.spacing
import ru.ermakov.myanime.core.R
import ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.components.PullToRefreshAnimeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    state: AnimeListState,
    onEvent: (AnimeListEvent) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullToRefreshState()
    Box {
        PullToRefreshContainer(
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        Column {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { value ->
                    onEvent(AnimeListEvent.SearchAnime(searchQuery = value))
                },
                placeholder = { Text(text = stringResource(id = R.string.search)) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = MaterialTheme.spacing.medium,
                        start = MaterialTheme.spacing.medium,
                        end = MaterialTheme.spacing.medium
                    )
            )
            PullToRefreshAnimeList(
                animeList = state.animeList,
                pullToRefreshState = pullToRefreshState,
                isLoading = state.isLoading,
                isRefreshing = state.isRefreshing,
                onRefresh = { onEvent(AnimeListEvent.RefreshAnime) },
                loadNextAnime = { onEvent(AnimeListEvent.LoadNextAnime) },
                onItemClicked = { /*navController.navigateToAnimeDetailsDestination(id = id)*/ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview() {
    MyAnimeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AnimeListScreen(
                state = AnimeListState(),
                onEvent = {},
                navController = rememberNavController()
            )
        }
    }
}