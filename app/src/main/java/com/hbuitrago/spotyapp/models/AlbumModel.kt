package com.hbuitrago.spotyapp.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumModel(
    val id: Int,
    val name: String,
    val  image: String
):Parcelable