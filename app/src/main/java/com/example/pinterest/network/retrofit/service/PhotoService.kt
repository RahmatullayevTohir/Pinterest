package com.example.pinterest.network.retrofit.service

import com.example.pinterest.model.PhotoModel
import com.example.pinterest.model.ReletedPhotos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {

    companion object {
        private const val accessKey = "-illmuSkheEwgGzVdufcspQltBwVOIz20Pwg9iq1k6o"
        private const val clientId = "Client-ID"
    }

    @Headers("Authorization: $clientId $accessKey")

    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ArrayList<PhotoModel>>

    @Headers("Authorization: Client-ID $accessKey")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<ReletedPhotos>

//    @GET("photos/{id}")
//    fun getPhotoById(@Path("id") id: Int): Call<PhotoModel>

}