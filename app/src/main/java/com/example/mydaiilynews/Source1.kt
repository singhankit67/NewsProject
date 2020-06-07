package com.example.mydaiilynews

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Source1{
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}