package com.hbuitrago.spotyapp.listener

import android.os.Bundle
import com.hbuitrago.spotyapp.models.AlbumModel
import com.hbuitrago.spotyapp.models.AlbumObjectModel

interface ListenerAlbum {
    fun onClickedAlbum(
        bundle: Bundle?,
        album: AlbumModel

    )
}