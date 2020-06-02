package com.example.mydaiilynews

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
interface NewsService {
   @get:GET("/v2/sources?language=en&apiKey=b2f868ee29a64c3bb200636a22e70181")
   val sources:Call<WebSite>
    @GET
    fun getNewsFromSource(@Url url:String):Call<News>
    }
