package com.example.movies

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ResourceRetrofit {
    companion object {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(interceptor)

        private val retrofit: Retrofit = Retrofit.Builder()
            .client(client.build())
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val resources: ApiService = retrofit.create(ApiService::class.java)

        private fun getHttpClient(): OkHttpClient {
            val httpBuilder = OkHttpClient.Builder()
            httpBuilder.addInterceptor(interceptor)
                .callTimeout(120, TimeUnit.SECONDS)
            return httpBuilder.build()
        }
    }
}