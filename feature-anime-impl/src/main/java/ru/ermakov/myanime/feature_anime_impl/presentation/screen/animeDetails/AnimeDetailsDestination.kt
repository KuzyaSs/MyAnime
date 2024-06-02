package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import ru.ermakov.myanime.core.presentation.navigation.Destination

object AnimeDetailsDestination {
    val route: String = Destination.AnimeDetails.name
    val animeIdArgument = "animeId"
    val routeWithArguments = "$route/{$animeIdArgument}"
    val arguments = listOf(
        navArgument(name = animeIdArgument) {
            type = NavType.IntType
        }
    )
}

fun NavGraphBuilder.animeDetailsDestination(navController: NavHostController) {
    composable(
        route = AnimeDetailsDestination.routeWithArguments,
        arguments = AnimeDetailsDestination.arguments
    ) { navBackStackEntry ->
        val animeId = navBackStackEntry.arguments?.getInt(AnimeDetailsDestination.animeIdArgument)
            ?: throw IllegalArgumentException()
        val animeDetailsViewModel: AnimeDetailsViewModel = koinViewModel()
        val state by animeDetailsViewModel.state.collectAsState()
        AnimeDetailsScreen(
            animeId = animeId,
            state = state,
            onEvent = animeDetailsViewModel::obtainEvent,
            navController = navController
        )
    }
}

fun NavHostController.navigateToAnimeDetailsDestination(animeId: Int) {
    this.navigate(AnimeDetailsDestination.route + "/$animeId")
}