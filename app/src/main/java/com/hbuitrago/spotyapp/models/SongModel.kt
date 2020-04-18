package com.hbuitrago.spotyapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongModel (
    @SerializedName("name")
    val title: String,
    @SerializedName("duration_ms")
    val  time: String,
    @SerializedName("spotify_url")
    val url: String
): Parcelable