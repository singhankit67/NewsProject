package com.example.mydaiilynews

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class News1 {
    @SerializedName("status")
    @Expose
    var status:String? = null
    @SerializedName("totalResult")
    @Expose
    var totalResult:Int? = null
    @SerializedName("articles")
    @Expose
    var article:MutableList<Article1>?= null
}