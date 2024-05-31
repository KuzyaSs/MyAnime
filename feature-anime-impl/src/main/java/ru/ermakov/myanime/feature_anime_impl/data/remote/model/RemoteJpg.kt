package ru.ermakov.myanime.feature_anime_impl.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteJpg(
    @SerializedName("image_url")
    val imageUrl: String,
)