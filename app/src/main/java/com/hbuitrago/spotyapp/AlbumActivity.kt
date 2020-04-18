package com.hbuitrago.spotyapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import com.hbuitrago.spotyapp.listener.ListenerAlbum
import kotlinx.android.synthetic.main.activity_album.*
import com.hbuitrago.spotyapp.models.AlbumModel
import com.hbuitrago.spotyapp.repository.SpotyRepository
import com.hbuitrago.spotyapp.utils.ITEM_ALBUM
import com.hbuitrago.spotyapp.utils.ValidateInternet
import java.lang.Exception

class AlbumActivity : AppCompatActivity(), ListenerAlbum {

    val validateInternet = ValidateInternet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)


        validateInternetToGetAlbums()

        recyclerViewAlbums.layoutManager = GridLayoutManager(this,2)

       /* val itemsAlbum = (0..20).map {item ->
            AlbumModel(item, "Titulo $item","" )

        }*/

           // recyclerViewAlbums.adapter=AlbumAdapter(itemsAlbum)

        }

    private fun validateInternetToGetAlbums(){
        if(validateInternet.isInternetAvailable(this)) {
            createThreadToGetAlbums()
        }else{
            AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(R.string.error_internet)
                .setPositiveButton("Reintentar"){_, _ ->
                    validateInternetToGetAlbums()
                }
                .setNegativeButton("Salir"){listener, _ ->
                    finish()
                }
                .create()
                .show()

        }
    }


    override fun onClickedAlbum(bundle: Bundle?, album: AlbumModel) {
        val intent = Intent(this, SongActivity::class.java)
        intent.putExtra(ITEM_ALBUM, album)
        startActivity(intent,bundle)
    }

    private fun createThreadToGetAlbums() {
        val thread = Thread(Runnable {
            getAlbumsFromRepository()
        })
        thread.start()
    }

    private fun getAlbumsFromRepository() {
        try {
            val repository = SpotyRepository()
            val result : List<AlbumModel> = repository.getAlbums(2)
            loadAdapter(result)
        }catch (e: Exception) {
            Toast.makeText(this, e.message ?: "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadAdapter(result: List<AlbumModel>) {
        runOnUiThread {
            recyclerViewAlbums.layoutManager = GridLayoutManager(this, 2)
            recyclerViewAlbums.adapter = AlbumAdapter(result, this)
        }

    }



}


