package com.example.mydaiilynews

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.Random
object Utils {
    var vibrantLightColorList = arrayOf<ColorDrawable>(ColorDrawable(Color.parseColor("#ffeead")), ColorDrawable(Color.parseColor("#93cfb3")), ColorDrawable(Color.parseColor("#fd7a7a")), ColorDrawable(Color.parseColor("#faca5f")), ColorDrawable(Color.parseColor("#1ba798")), ColorDrawable(Color.parseColor("#6aa9ae")), ColorDrawable(Color.parseColor("#ffbf27")), ColorDrawable(Color.parseColor("#d93947")))
    val randomDrawbleColor:ColorDrawable
        get() {
            val idx = Random().nextInt(vibrantLightColorList.size)
            return vibrantLightColorList[idx]
        }
    val country:String
        get() {
            val locale = Locale.getDefault()
            val country = (locale.getCountry()).toString()
            return country.toLowerCase()
        }
    fun DateToTimeFormat(oldstringDate:String): String? {
        val p = PrettyTime(Locale(country))
        var isTime: String? = null
        try
        {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH)
            val date = sdf.parse(oldstringDate)
            isTime = p.format(date)
        }
        catch (e:ParseException) {
            e.printStackTrace()
        }
        return isTime
    }
    @SuppressLint("SimpleDateFormat")
    fun DateFormat(oldstringDate:String):String {
        var newDate:String
        val dateFormat = SimpleDateFormat("E, d MMM yyyy", Locale(country))
        try
        {
            val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            newDate = dateFormat.format(date)
        }
        catch (e:ParseException) {
            e.printStackTrace()
            newDate = oldstringDate
        }
        return newDate
    }
}