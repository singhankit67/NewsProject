package com.example.mydaiilynews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiInterface {
//    @get:GET("/v2/top-headlines?language=en&apiKey=71a46c4dac0f4761becf0206e7d67f1f")
    @GET
    fun getNews(@Url url: String): Call<News1>
//    val sources1:Call<News1>
    @GET
    fun searchAnything(@Url url1:String): Call<News1>
}