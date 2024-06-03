package ru.ermakov.myanime.feature_anime_impl.data.mapper

import ru.ermakov.myanime.feature_anime_api.data.local.model.LocalAnime
import ru.ermakov.myanime.feature_anime_impl.data.remote.model.RemoteAnime
import ru.ermakov.myanime.feature_anime_api.domain.model.Anime

fun Anime.toLocalAnime(): LocalAnime {
    return LocalAnime(
        id = id,
        url = url,
        image = image,
        title = title,
        type = type,
        episodes = episodes,
        score = score,
        rank = rank,
        popularity = popularity,
        members = members,
        favorites = favorites,
        synopsis = synopsis,
        background = background,
        year = year
    )
}

fun LocalAnime.toAnime(): Anime {
    this.apply {
        return Anime(
            id = id,
            url = url,
            image = image,
            title = title,
            type = type,
            episodes = episodes,
            score = score,
            rank = rank,
            popularity = popularity,
            members = members,
            favorites = favorites,
            synopsis = synopsis,
            background = background,
            year = year
        )
    }
}

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