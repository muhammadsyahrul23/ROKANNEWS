package com.muhammad.syahrul

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.muhammad.syahrul.databinding.ActivityDetailBinding
import com.muhammad.syahrul.remote.POJO.ArticlesItem

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataIntent: ArticlesItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = layoutInflater
        binding = ActivityDetailBinding.inflate(inflater)

        setContentView(binding.root)

        dataIntent = intent.getParcelableExtra("berita_item") as ArticlesItem

        // isi webview Client
        binding.webview.run {
            webChromeClient = MyWebChromeClient()
            webViewClient = MyWebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(dataIntent.url)
        }

    }

    // Buat WebChromeClient
    internal class MyWebChromeClient : WebChromeClient()

    // Buat WebViewClient
    internal class MyWebViewClient : WebViewClient()
}