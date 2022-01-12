package com.example.globalnews.activities

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.globalnews.R
import kotlinx.android.synthetic.main.activity_web_view_detail.*
import retrofit2.http.GET

class WebViewDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view_detail)
        val getUrl = intent.getStringExtra("url")
        if (getUrl != null){
            webViewDetails.settings.javaScriptEnabled = true
            val connectivityManger = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)

            webViewDetails.webViewClient = object  : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    webViewDetails.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            }
            webViewDetails.loadUrl(getUrl)
        }
    }
}