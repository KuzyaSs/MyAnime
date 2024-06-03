package ru.ermakov.myanime.core.presentation

import android.content.Context
import ru.ermakov.myanime.core.R
import ru.ermakov.myanime.core.domain.model.CoreError
import ru.ermakov.myanime.core.domain.model.RootError

fun RootError.toStringCoreError(context: Context): String {
    return when (this) {
        is CoreError -> when (this) {
            CoreError.CONNECTION_FAILURE -> context.getString(R.string.connection_failure)
            CoreError.DATABASE_ERROR -> context.getString(R.string.read_write_error)
        }

        else -> throw UnsupportedOperationException()
    }
}