package com.example.pixebay

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixeApi {
    @GET("api/?key=30126344-41522a727c59915668240ed5a")
    fun getImages(
        @Query("q") q: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Call<PixabayModel>

}