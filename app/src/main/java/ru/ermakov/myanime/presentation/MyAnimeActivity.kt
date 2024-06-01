package ru.ermakov.myanime.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.ermakov.myanime.app.MyAnimeApplication
import ru.ermakov.myanime.core.presentation.navigation.Destination
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.AnimeListScreen
import ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.AnimeListState
import ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.AnimeListViewModel
import ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.animeListDestination

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAnimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    MyAnimeAppScreen(navController = navController)
                }
            }
        }
    }

    @Composable
    fun MyAnimeAppScreen(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = Destination.AnimeList.name
        ) {
            animeListDestination(navController = navController)
        }
    }
}