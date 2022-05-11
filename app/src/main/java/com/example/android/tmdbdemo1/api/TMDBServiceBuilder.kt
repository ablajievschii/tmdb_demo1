package com.example.android.tmdbdemo1.api

import com.example.android.tmdbdemo1.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit

object TMDBServiceBuilder {

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = BASIC
        })
        .addInterceptor(AddApiKeyInterceptor()) // TODO add api_key append interceptor
        .build()


    fun <T> buildService(
        service: Class<T>
    ): T {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .client(client)
            .build()
            .create(service)
    }
}