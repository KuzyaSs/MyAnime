package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeDetails.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.core.presentation.theme.spacing
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime
import ru.ermakov.myanime.feature_anime_api.R

@Composable
fun SuccessfulAnimeDetailsScreen(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            model = anime.image,
            loading = {
                Box {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(350.dp)
                .padding(bottom = MaterialTheme.spacing.medium)
        )
        Text(
            text = anime.title,
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.small),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "${stringResource(id = R.string.type)} \n${anime.type}",
                textAlign = TextAlign.Center
            )
            Text(
                text = "${stringResource(id = R.string.episodes)} \n${anime.episodes}",
                textAlign = TextAlign.Center
            )
            Text(
                text = "${stringResource(id = R.string.year)} \n${anime.year}",
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "${stringResource(id = R.string.rank)} \n${anime.rank}",
                textAlign = TextAlign.Center
            )
            Text(
                text = "${stringResource(id = R.string.popularity)} \n${anime.popularity}",
                textAlign = TextAlign.Center
            )
        }
        Text(text = anime.synopsis, textAlign = TextAlign.Justify)
    }
}

@Preview(showBackground = true)
@Composable
fun SuccessfulAnimeDetailsScreenPreview() {
    MyAnimeTheme {
        Surface {
            SuccessfulAnimeDetailsScreen(
                anime = Anime(
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
                ),
                modifier = Modifier.padding(all = MaterialTheme.spacing.medium)
            )
        }
    }
}