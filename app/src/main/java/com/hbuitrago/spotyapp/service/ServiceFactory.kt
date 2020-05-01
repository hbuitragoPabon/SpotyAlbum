package com.hbuitrago.spotyapp.service

import com.google.gson.GsonBuilder
import com.hbuitrago.spotyapp.repository.UserServices
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceFactory {

    private val API_BASE_PATH = "https://i8rmpiaad2.execute-api.us-east-1.amazonaws.com/dev/api/"
    private val API_USER_PATH = "https://shoppingproducts.herokuapp.com/"
    private var restAdapter: Retrofit? = null

    fun servicesFactory(URL: String) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
        this.restAdapter = Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(getGsonConverter())
            .build()
    }


    private fun getGsonConverter(): Converter.Factory {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd")
            .create()
        return GsonConverterFactory.create(gson)
    }

    fun getInstanceSpotyService(): SpotyServices {
        servicesFactory(API_BASE_PATH)
        return restAdapter!!.create(SpotyServices::class.java)
    }

    fun getInstanceUserService(): UserServices  {
        servicesFactory(API_USER_PATH)
        return restAdapter!!.create(UserServices::class.java)
    }

}