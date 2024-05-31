package ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

@Composable
fun AnimeItem(anime: Anime, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth()) {
        SubcomposeAsyncImage(
            model = anime.image,
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = null,
            modifier = Modifier.weight(0.3f),
            alignment = Alignment.TopCenter
        )
        Column(modifier = Modifier.weight(0.7f)) {
            Text(text = anime.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = anime.synopsis, maxLines = 5, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeItemPreview() {
    MyAnimeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            AnimeItem(
                anime = Anime(
                    id = 1,
                    url = "https://myanimelist.net/anime/19/Monster",
                    image = "https://cdn.myanimelist.net/images/anime/10/18793.jpg",
                    title = "Monster",
                    type = "TV",
                    episodes = 1,
                    score = 1,
                    rank = 1,
                    popularity = 1,
                    members = 1,
                    favorites = 1,
                    synopsis = "Dr. Kenzou Tenma, an elite neurosurgeon recently engaged to his hospital director's daughter, is well on his way to ascending the hospital hierarchy. That is until one night, a seemingly small event changes Dr. Tenma's life forever. While preparing to perform surgery on someone, he gets a call from the hospital director telling him to switch patients and instead perform life-saving brain surgery on a famous performer. His fellow doctors, fiancée, and the hospital director applaud his accomplishment; but because of the switch, a poor immigrant worker is dead, causing Dr. Tenma to have a crisis of conscience.\\n\\nSo when a similar situation arises, Dr. Tenma stands his ground and chooses to perform surgery on the young boy Johan Liebert instead of the town's mayor. Unfortunately, this choice leads to serious ramifications for Dr. Tenma—losing his social standing being one of them. However, with the mysterious death of the director and two other doctors, Dr. Tenma's position is restored. With no evidence to convict him, he is released and goes on to attain the position of hospital director. \\n\\nNine years later when Dr. Tenma saves the life of a criminal, his past comes back to haunt him—once again, he comes face to face with the monster he operated on. He must now embark on a quest of pursuit to make amends for the havoc spread by the one he saved.\\n\\n[Written by MAL Rewrite]",
                    background = "",
                    year = 2004
                )
            )
        }
    }
}