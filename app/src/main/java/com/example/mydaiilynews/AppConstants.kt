package com.example.mydaiilynews

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
object AppConstants {
    val API_KEY = "b2f868ee29a64c3bb200636a22e70181"
    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun parse(input:String):Date {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
        var input1: String? = null
        if (input1!!.endsWith("Z"))
        {
            input1 = input.substring(0, input.length - 1) + "GMT-00:00"
        }
        else
        {
            val inset = 6
            val s0 = input1.substring(0, input1.length - inset)
            val s1 = input1.substring(input1.length - inset, input.length)
            input1 = s0 + "GMT" + s1
        }
        return df.parse(input1)
    }
}