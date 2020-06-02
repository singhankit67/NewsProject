package com.example.mydaiilynews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
interface ApiInterface {
   // @GET("v2/sources?language=en&apiKey=b2f868ee29a64c3bb200636a22e70181")
    //var sources:Call<WebSite>
   @get:GET("sources?apiKey=71a46c4dac0f4761becf0206e7d67f1f")
   val sources: Call<WebSite>

    @GET
    fun getNewsestArticles(@Url url:String):Call<News>
}