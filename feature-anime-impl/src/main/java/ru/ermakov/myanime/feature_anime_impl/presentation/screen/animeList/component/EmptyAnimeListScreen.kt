package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ru.ermakov.myanime.core.R
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.core.presentation.theme.spacing
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.AnimeListEvent

@Composable
fun EmptyAnimeListScreen(onEvent: (AnimeListEvent) -> Unit, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.nothing_found),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
            )
            Button(onClick = { onEvent(AnimeListEvent.Retry) }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyAnimeListScreenPreview() {
    MyAnimeTheme {
        Surface {
            EmptyAnimeListScreen(onEvent = {})
        }
    }
}