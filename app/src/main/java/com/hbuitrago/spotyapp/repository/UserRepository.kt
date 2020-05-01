package com.hbuitrago.spotyapp.repository

import com.hbuitrago.spotyapp.models.UserModel
import com.hbuitrago.spotyapp.service.ServiceFactory
import com.hbuitrago.spotyapp.service.SpotyServices

class UserRepository {
    private  var userServices: UserServices

    init {
        val ServiceFactory = ServiceFactory()
        userServices = ServiceFactory.getInstanceUserService()
    }

    fun createUser(user: UserModel): UserModel {
        try {
            val call: retrofit2.Call<UserModel> = userServices.CreateUser(user)
            val response = call.execute()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw Exception(response.errorBody().toString())
            }
        } catch (exception: Exception) {
            throw exception
        }
    }




}