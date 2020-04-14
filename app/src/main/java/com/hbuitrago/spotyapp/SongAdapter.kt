package com.hbuitrago.spotyapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import com.hbuitrago.spotyapp.models.SongModel

class SongAdapter(val listSong: List<SongModel>): RecyclerView.Adapter<SongHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_song,parent,false))
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.bindSong(listSong[position])
    }


}