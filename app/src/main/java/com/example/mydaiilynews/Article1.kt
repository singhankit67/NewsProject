package com.example.mydaiilynews

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Article1 {
    @SerializedName("source")
    @Expose
    var source1:Source1? = null
    @SerializedName("author")
    @Expose
    var author:String? = null
    @SerializedName("title")
    @Expose
    var title:String ? = null
    @SerializedName("description")
    @Expose
    var description:String? = null
    @SerializedName("url")
    @Expose
    var url:String ? = null
    @SerializedName("urlToImage")
    @Expose
    var urlToImage:String ? = null
    @SerializedName("publishedAt")
    @Expose
    var publishedAt:String? = null

}