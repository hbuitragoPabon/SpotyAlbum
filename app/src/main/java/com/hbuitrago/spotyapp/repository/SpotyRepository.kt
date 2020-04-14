package com.hbuitrago.spotyapp.repository

import android.provider.MediaStore
import com.hbuitrago.spotyapp.models.AlbumModel
import com.hbuitrago.spotyapp.models.AlbumObjectModel
import com.hbuitrago.spotyapp.models.SongModel
import com.hbuitrago.spotyapp.models.SongObjectModel
import com.hbuitrago.spotyapp.service.ServiceFactory
import com.hbuitrago.spotyapp.service.SpotyServices
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class SpotyRepository {

    private var spotyServices: SpotyServices

    init {
        val serviceFactory = ServiceFactory()
        spotyServices = serviceFactory.getInstanceSpotyService()
    }

    fun getAlbums(artists: Int): List<AlbumModel> {
        try {
            val call: Call<List<AlbumObjectModel>> = spotyServices.getAlbums(artists)
            val response: Response<List<AlbumObjectModel>> = call.execute()
            if (response.isSuccessful) {
                return response.body()!![0].albums
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (e: Exception) {
            throw  e
        }
    }

    fun getSongsByAlbum(albumId: Int): List<SongModel> {
        try {
        val call: Call<List<SongObjectModel>> = spotyServices.getSongsByAlbum(albumId)
        val response: Response<List<SongObjectModel>> = call.execute()
        if (response.isSuccessful) {
            return response.body()!![0].songs
        } else {
            throw Exception(response.errorBody().toString())
        }
    }catch (e: Exception) {
        throw  e
    }
}

}

