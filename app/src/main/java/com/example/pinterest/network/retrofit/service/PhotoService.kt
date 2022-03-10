package com.example.pinterest.network.retrofit.service

import com.example.pinterest.model.PhotoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {

    companion object {
        private const val accessKey = "Y2TzcscjJ_dKraHyZDnVE1BR_u7xiBlALdFjuC18scw"
        private const val clientId = "Client-ID"
    }

    @Headers("Authorization:$clientId $accessKey")

    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ArrayList<PhotoModel>>

    @GET("photos/{id}")
    fun getPhotoById(@Path("id") id: Int): Call<PhotoModel>

}