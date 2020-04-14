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

class AlbumAdapter(val data:List<AlbumModel>, val  listener: ListenerAlbum) : RecyclerView.Adapter<AlbumAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        )
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
                /*val intent = Intent(view.context,SongActivity::class.java)
                val param1: Pair<View, String> = Pair(view.imgAlbum,"transitionAlbumImage")
                val param2: Pair<View, String> = Pair(view.txtTitle,"transitionAlbumTitle")
                //val animation: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(view.context as Activity, param1, param2)
                view.context.startActivity(intent)
                listener.onClickedAlbum()*/

               /* val params = ArrayList<androidx.core.util.Pair<View,String>>()
                params.add(androidx.core.util.Pair(view.imgAlbum, "transitionAlbumImage"))
                params.add(androidx.core.util.Pair(view.txtTitle, "transitionAlbumTitle"))
                //val paramsArray: Array<androidx.core.util.Pair<View, String>> = params.toTypedArray()
                val animation: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(view.context as Activity, *params.toTypedArray())*/

                //view.context.startActivity(intent, animation.toBundle())
                //listener.onClickedAlbum(animation.toBundle(), itemAlbumModel)


            }
        }
    }


}