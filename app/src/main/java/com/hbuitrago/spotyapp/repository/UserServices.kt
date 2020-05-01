package com.hbuitrago.spotyapp.repository

import com.hbuitrago.spotyapp.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserServices {
    @POST("users")
    fun CreateUser(@Body user: UserModel) : Call<UserModel>
}