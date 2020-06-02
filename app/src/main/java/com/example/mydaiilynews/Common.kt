package com.example.mydaiilynews

import android.annotation.SuppressLint
import retrofit2.create
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
object Common {
    val API_KEY = "71a46c4dac0f4761becf0206e7d67f1f"
    val BASE_URL = "https://newsapi.org"
    val newsService:NewsService
    get() = RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)//calling the the string  here it concatenates the base url with the
    fun getNewsApi(source:String):String{
        val apiUrl = StringBuilder(" https://newsapi.org/v2/top-headlines?sources=")
            .append(source)
            .append("&apiKey=")
            .append(API_KEY)
            .toString()
        return apiUrl

    }
    //our api key and stores the source and status in the website class
    //inShort we implemented the inteface news service here
//    @SuppressLint("SimpleDateFormat")
//    @Throws(ParseException::class)
//    fun parse(input:String):Date {
//        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
//        var input1: String? = null
//        if (input1!!.endsWith("Z"))
//        {
//            input1 = input.substring(0, input.length - 1) + "GMT-00:00"
//        }
//        else
//        {
//            val inset = 6
//            val s0 = input1.substring(0, input1.length - inset)
//            val s1 = input1.substring(input1.length - inset, input.length)
//            input1 = s0 + "GMT" + s1
//        }
//        return df.parse(input1)
//    }
}