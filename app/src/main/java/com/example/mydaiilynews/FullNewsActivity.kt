package com.example.mydaiilynews
import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_full_news.*

class FullNewsActivity : AppCompatActivity() {
    lateinit var dialog: SpotsDialog

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_news)
        dialog = SpotsDialog(this)
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
}
