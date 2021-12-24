package com.muhammad.syahrul.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    // fungsi HttpLoggingInterceptor adalah mnegecek status response dari server
    // Keterangan status server di LogCat :
    // 200 = response server
    // 404 = url not found
    // 401 = tidak ada otorasi / API key belum di masukkan
    // 501 = masalah di server
    private fun interceptor() : Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    // client bertugas sebagai alat penghubung ke server
    // disini tempat kita memasukkan interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(interceptor()).build()

    // retrofit bertugas sebagai pengatur Client
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client).build()


    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        requestBuilder.addHeader("Authorization", "ce37f3b120dc423d809ca02752876d6d")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
