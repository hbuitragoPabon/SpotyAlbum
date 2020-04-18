package com.hbuitrago.spotyapp.listener

import com.hbuitrago.spotyapp.models.SongModel

interface ListenerSong {
    fun onClickedSong(
        song: SongModel
    )
}