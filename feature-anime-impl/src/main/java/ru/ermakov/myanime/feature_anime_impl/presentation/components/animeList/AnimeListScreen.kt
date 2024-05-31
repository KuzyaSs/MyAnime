package ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.ermakov.myanime.core.R
import ru.ermakov.myanime.core.presentation.theme.MyAnimeTheme
import ru.ermakov.myanime.core.presentation.theme.spacing
import ru.ermakov.myanime.feature_anime_impl.presentation.components.animeList.components.AnimeList

@Composable
fun AnimeListScreen(state: AnimeListState, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(all = MaterialTheme.spacing.medium)) {
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
        AnimeList(animeList = state.animeList)
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview() {
    MyAnimeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            AnimeListScreen(state = AnimeListState())
        }
    }
}