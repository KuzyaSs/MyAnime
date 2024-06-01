package ru.ermakov.myanime.feature_anime_impl.data.mapper

import ru.ermakov.myanime.feature_anime_impl.data.remote.model.RemoteAnime
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

fun RemoteAnime.toAnime(): Anime {
    this.apply {
        return Anime(
            id = id,
            url = url ?: "",
            image = image.remoteJpg.imageUrl ?: "",
            title = title ?: "",
            type = type ?: "",
            episodes = episodes,
            score = score,
            rank = rank,
            popularity = popularity,
            members = members,
            favorites = favorites,
            synopsis = synopsis ?: "",
            background = background ?: "",
            year = year
        )
    }
}