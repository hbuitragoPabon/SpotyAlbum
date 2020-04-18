package com.hbuitrago.spotyapp

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hbuitrago.spotyapp.models.SongModel
import kotlinx.android.synthetic.main.item_song.view.*

class SongHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun bindSong(songModel: SongModel){
        view.txtTitleSong.text = songModel.title
        view.txtDurationSong.text = calculateTime(songModel.time)
        /*view.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(songModel.url))
            if(intent.resolveActivity(view.context.packageManager) != null) {
                view.context.startActivity(intent)
            }
        }*/

    }

    private fun calculateTime(duration: String): String{
        val minutes = duration.toInt() / 1000 /60
        val seconds = duration.toInt() / 1000 % 60
        return "$minutes:${if(seconds <10) "0$seconds" else seconds}"

    }
}