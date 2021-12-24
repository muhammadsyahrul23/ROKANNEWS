package com.muhammad.syahrul

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.muhammad.syahrul.databinding.ActivityOnlineBinding
import com.muhammad.syahrul.remote.POJO.ArticlesItem
import com.muhammad.syahrul.remote.RetrofitInterfaces
import com.muhammad.syahrul.remote.RetrofitService
import com.muhammad.syahrul.rv.ItemListNewsAdapter
import kotlinx.coroutines.launch

class OnlineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnlineBinding

    // deklarasikan adapter yang akan digunakan pada recyclerview
    private lateinit var adapterRV: ItemListNewsAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            val inflater = layoutInflater
            binding = ActivityOnlineBinding.inflate(inflater)

            setContentView(binding.root)

            setSupportActionBar(binding.homeToolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            Glide.with(this).load(R.drawable.rokan_news).into(binding.imgToolbar)

            // isi variable adapterRV dengan class ItemListNewsAdapter
            adapterRV = ItemListNewsAdapter()

            // Atur recyclerview rvnews yang akan digunakan
//            binding.rvNews.setHasFixedSize(true)
//            binding.rvNews.layoutManager = LinearLayoutManager(this)
//            binding.rvNews.adapter = adapterRV
            // versi simpelnya
            binding.rvNews.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@OnlineActivity)
                adapter = adapterRV
            }

            val retrofitService =  RetrofitService.buildService(RetrofitInterfaces::class.java)
            lifecycleScope.launch {
                val request = retrofitService.topHeadlines("id")
                if (request.isSuccessful) {
                    Toast.makeText(
                        this@OnlineActivity,
                        request.body()?.totalResults.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                    request.body()?.articles?.let { data ->
                        // Buat sebuah array kosong yang hanya bisa diisi oleh ArticleItem
                        val dataResult = arrayListOf<ArticlesItem>()
                        data.forEach { article ->
                            // jika item tidak bernilai null maka
                            article?.let {
                                // tambahkan item ke dalam DataResult
                                dataResult.add(it)
                            }
                        }
                        // masukkan dataResult ke dalam adapterRV
                        adapterRV.addData(dataResult)
                    }
                } else {
                    Log.e("OnlineActivity", request.message())
                }
            }
        }
    }
