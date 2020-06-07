package com.example.mydaiilynews
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_full_news.*

class FullNewsActivity : AppCompatActivity() {
    lateinit var dialog: SpotsDialog
    var myActivity :Activity? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun backGroundColor() {
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window?.navigationBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        window?.setBackgroundDrawableResource(R.drawable.background)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_news)
        backGroundColor()

        dialog = SpotsDialog(this,R.style.Custom)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dialog.show()
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                dialog.dismiss()
            }
        }
        if (intent != null){
            if(!intent.getStringExtra("webURL").isEmpty()){
                webView.loadUrl(intent.getStringExtra("webURL"))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
