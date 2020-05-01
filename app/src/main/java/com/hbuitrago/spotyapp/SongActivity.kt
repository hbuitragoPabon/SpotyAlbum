package com.hbuitrago.spotyapp

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.hbuitrago.spotyapp.listener.ListenerSong
import com.hbuitrago.spotyapp.models.AlbumModel
import com.hbuitrago.spotyapp.models.SongModel
import com.hbuitrago.spotyapp.repository.SpotyRepository
import com.hbuitrago.spotyapp.utils.ITEM_ALBUM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_song.*
import java.lang.Exception

class SongActivity : AppCompatActivity(), ListenerSong {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        rvSongs.layoutManager = LinearLayoutManager(this)

        /*val songs = (0..20).map {
            SongModel("Cancion $it", "1:10", "")
        }*/

        /*val adapterSong = SongAdapter(songs)
        rvSongs.adapter = adapterSong*/
        var album : AlbumModel? = null

        /*intent?.let {myIntent ->
            val album = myIntent.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            Picasso.with(this).load(album.image).into(imgHeaderDetail)
            txtTitleDetail.text = album.name

        }*/

        intent?.let {myIntent ->
            album = myIntent.getParcelableExtra<AlbumModel>(ITEM_ALBUM)
            Picasso.with(this).load(album!!.image).into(imgHeaderDetail)
            txtTitleDetail.text = album!!.name
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
           val adapterSong= SongAdapter(result, this)
            rvSongs.adapter = adapterSong
        }
    }

    override fun onClickedSong(urlSong: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(urlSong))
        if(intent.resolveActivity(this.packageManager) != null) {
            startActivity(intent)
        }
    }

    /*override fun onClickedSong(song: SongModel) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(song.url))
        intent.putExtra(SONG_ALBUM,song)
       startActivity(intent)

    }*/
}
