package com.hbuitrago.spotyapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hbuitrago.spotyapp.models.AlbumModel
import com.hbuitrago.spotyapp.models.SongModel
import com.hbuitrago.spotyapp.repository.SpotyRepository
import com.hbuitrago.spotyapp.utils.ITEM_ALBUM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*
import java.lang.Exception

class SongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        rvSongs.layoutManager = LinearLayoutManager(this)
        val songs = (0..20).map {
            SongModel("Cancion $it", "1:10", "")
        }

        /*val adapterSong = SongAdapter(songs)
        rvSongs.adapter = adapterSong*/
        var album : AlbumModel? = null

        intent?.let {myIntent ->
            val album = myIntent.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            Picasso.with(this).load(album.image).into(imgHeaderDetail)
            txtTitleDetail.text = album.name
        }

        createThreadToGetSongs(album!!.id)

    }

    private fun createThreadToGetSongs(idAlbum:Int) {
        val thread = Thread(Runnable {
            getSongsFromRepository(idAlbum)
        })
        thread.start()
    }

    private fun getSongsFromRepository(idAlbum: Int) {
        try {
            val repository = SpotyRepository()
            val result : List<SongModel> = repository.getSongsByAlbum(idAlbum)
            loadAdapter(result)
        }catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadAdapter(result: List<SongModel>) {
        runOnUiThread {
           val adapterSong= SongAdapter(result)
            rvSongs.adapter = adapterSong
        }
    }
}
