package com.hbuitrago.spotyapp.models

import com.google.gson.annotations.SerializedName

data class SongModel (
    @SerializedName("name")
    val title: String,
    @SerializedName("duration_ms")
    val  time: String,
    @SerializedName("spotify_url")
    val url: String
)