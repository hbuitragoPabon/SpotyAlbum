package com.hbuitrago.spotyapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hbuitrago.spotyapp.listener.ListenerSong
import com.hbuitrago.spotyapp.models.SongModel
import kotlinx.android.synthetic.main.item_song.view.*

class SongAdapter(
    val listSong: List<SongModel>,
    val songListener: ListenerSong
): RecyclerView.Adapter<SongHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_song,
                parent,
                false
            ),songListener
        )
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bindSong(listSong[position])
    }



    /*inner class SongHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bindSong(songModel: SongModel){
            view.txtTitleSong.text = songModel.title
            view.txtDurationSong.text = calculateTime(songModel.time)
            view.setOnClickListener{
                listener.onClickedSong(songModel)
            }

        }
    }

    private fun calculateTime(duration: String): String{
        val minutes = duration.toInt() / 1000 /60
        val seconds = duration.toInt() / 1000 % 60
        return "$minutes:${if(seconds <10) "0$seconds" else seconds}"

    }*/
}