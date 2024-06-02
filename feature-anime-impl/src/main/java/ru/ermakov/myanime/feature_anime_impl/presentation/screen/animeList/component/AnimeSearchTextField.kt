package ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.ermakov.myanime.core.R
import ru.ermakov.myanime.core.presentation.theme.spacing
import ru.ermakov.myanime.feature_anime_impl.presentation.screen.animeList.AnimeListEvent

@Composable
fun AnimeSearchTextField(
    searchQuery: String,
    onEvent: (AnimeListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
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
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium
            )
    )
}