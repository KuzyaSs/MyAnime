package ru.ermakov.myanime.feature_anime_api.presentation

import android.content.Context
import ru.ermakov.myanime.core.domain.model.CoreError
import ru.ermakov.myanime.core.domain.model.RootError
import ru.ermakov.myanime.core.presentation.toStringCoreError
import ru.ermakov.myanime.feature_anime_api.domain.model.AnimeError

fun RootError.toStringAnimeError(context: Context): String {
    return when (this) {
        is CoreError -> this.toStringCoreError(context = context)

        is AnimeError -> {
            throw NotImplementedError()
        }

        else -> {
            throw UnsupportedOperationException()
        }
    }
}