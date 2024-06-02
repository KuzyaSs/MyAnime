package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import org.koin.androidx.compose.koinViewModel
import ru.ermakov.myanime.core.presentation.navigation.Destination

object AnimeListDestination {
    val route: String = Destination.AnimeList.name
}

fun NavGraphBuilder.animeListDestination(navController: NavHostController) {
    composable(route = AnimeListDestination.route) {
        val animeListViewModel: AnimeListViewModel = koinViewModel()
        val state by animeListViewModel.state.collectAsState()
        AnimeListScreen(
            state = state,
            onEvent = animeListViewModel::obtainEvent,
            navController = navController
        )
    }
}