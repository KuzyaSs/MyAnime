package ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

@Composable
fun AnimeList(animeList: List<Anime>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(animeList) { anime ->
            AnimeItem(anime = anime)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListPreview() {
    MyAnimeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AnimeList(animeList = emptyList())
        }
    }
}