package com.knitterassignment.repository.network

import com.knitterassignment.util.Constants.Companion.accessTokenKey
import com.knitterassignment.util.Constants.Companion.accessTokenValue
import okhttp3.Interceptor
import okhttp3.Response

class HTTPInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url.newBuilder()
            .addQueryParameter( accessTokenKey, accessTokenValue)
            .addQueryParameter("format", "json")
            .build()

        val newRequest = request.newBuilder()
            .url(url)
            .build()

        return chain.proceed(newRequest)
    }
}