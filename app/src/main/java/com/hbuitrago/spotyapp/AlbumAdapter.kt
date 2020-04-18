package com.hbuitrago.spotyapp


import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import com.hbuitrago.spotyapp.listener.ListenerAlbum
import kotlinx.android.synthetic.main.item_album.view.*
import com.hbuitrago.spotyapp.models.AlbumModel
import com.squareup.picasso.Picasso
import android.support.v4.util.Pair

class AlbumAdapter(val data:List<AlbumModel>, val  listener: ListenerAlbum) : RecyclerView.Adapter<AlbumAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindItem(data[position])
    }

    inner class Holder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(itemAlbumModel: AlbumModel) {
            Picasso.with(view.context).load(itemAlbumModel.image).into(view.imgAlbum)
            view.txtTitle.text = itemAlbumModel.name
            view.setOnClickListener{

                val params = ArrayList<Pair<View,String>>()
                params.add(Pair(view.imgAlbum, "transitionAlbumImage"))
                params.add(Pair(view.txtTitle, "transitionAlbumTitle"))
                //val intent = Intent(view.context,SongActivity::class.java)
// val paramsArray: Array<Pair<View, String>> = params.toTypedArray()
                val animation: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(view.context as Activity, *params.toTypedArray())



                listener.onClickedAlbum(animation.toBundle(), itemAlbumModel )

            }
        }
    }


}