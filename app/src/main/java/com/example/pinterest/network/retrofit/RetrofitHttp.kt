package com.example.pinterest.network.retrofit

import com.example.pinterest.network.retrofit.service.PhotoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {

    val SERVER_DEVELOPMENT = "https://api.unsplash.com/"

    val retrofit = Retrofit.Builder().baseUrl(SERVER_DEVELOPMENT).addConverterFactory(GsonConverterFactory.create()).build()

    val apiService: PhotoService = retrofit.create(PhotoService::class.java)
}