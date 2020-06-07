package com.example.mydaiilynews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.content.ContextCompat

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fun backGroundColor() {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
            window.setBackgroundDrawableResource(R.drawable.background)
        }
            setContentView(R.layout.activity_splash)
            backGroundColor()
            Handler().postDelayed({
                val startAct = Intent(this@SplashActivity, Main2Activity::class.java)
                startActivity(startAct)
                this.finish()
            }, 2000)
        }

}
