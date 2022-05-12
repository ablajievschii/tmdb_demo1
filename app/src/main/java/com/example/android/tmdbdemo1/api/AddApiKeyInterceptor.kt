package com.example.android.tmdbdemo1.api

import com.example.android.tmdbdemo1.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class AddApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val url = original.url
        val urlBuilder = HttpUrl.Builder()
            .scheme(url.scheme)
            .host(url.host)
            .port(url.port)
            // add original request path segments
            .addPathSegments(url.encodedPathSegments.joinToString("/"))
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
        // append original url query parameters
        url.queryParameterNames.forEach { name ->
            urlBuilder.addQueryParameter(name, url.queryParameter(name))
        }

        val request = original.newBuilder()
            .url(urlBuilder.build())
            .build()
        return chain.proceed(request)
    }
}