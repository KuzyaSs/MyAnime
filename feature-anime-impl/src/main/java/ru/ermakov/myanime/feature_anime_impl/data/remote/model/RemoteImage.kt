package ru.ermakov.myanime.feature_anime_impl.data.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteImage(
    @SerializedName("jpg")
    val remoteJpg: RemoteJpg,
)