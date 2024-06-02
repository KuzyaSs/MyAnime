package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.core.presentation.theme.spacing
import ru.ermakov.myanime.feature_anime_api.presentation.toStringAnimeError
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails.component.ErrorAnimeDetailsScreen
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails.component.SuccessfulAnimeDetailsScreen

@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    state: AnimeDetailsState,
    onEvent: (AnimeDetailsEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(true) {
        onEvent(AnimeDetailsEvent.EnterAnimeDetailsScreen(animeId = animeId))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = MaterialTheme.spacing.medium)
            .verticalScroll(state = rememberScrollState())
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.medium)
                .clickable { navController.popBackStack() }
        )
        when {
            (state.isLoading && state.anime == null) -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    CircularProgressIndicator()
                }
            }

            (state.isError && state.error != null) -> {
                ErrorAnimeDetailsScreen(
                    errorMessage = state.error.toStringAnimeError(context = LocalContext.current),
                    onEvent = { event -> onEvent(event) },
                    modifier = Modifier.weight(1f)
                )
            }

            (state.anime != null) -> {
                SuccessfulAnimeDetailsScreen(
                    anime = state.anime,
                    onEvent = { event -> onEvent(event) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeDetailsScreenPreview() {
    MyAnimeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AnimeDetailsScreen(
                animeId = 1,
                state = AnimeDetailsState(
                    isLoading = true,
                ),
                onEvent = {},
                navController = rememberNavController()
            )
        }
    }
}
