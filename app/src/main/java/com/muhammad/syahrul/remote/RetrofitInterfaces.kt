package com.muhammad.syahrul.remote

import com.muhammad.syahrul.remote.POJO.ResponseNews
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterfaces {

    //http://newsapi.org/v2/top-headlines?country=id&apiKey=API_KEY_ANTUM
    // Get URL path: /v2/top-headlines
    // http://newsapi.org/v2/top-headlines
    @GET("/v2/top-headlines")
    suspend fun topHeadlines(
        // Menambah Query Country
        // sehingga URL menjadi
        // http://newsapi.org/v2/top-headlines?country=id
        @Query("country") country: String
    ) : retrofit2.Response<ResponseNews>
}