package com.knitterassignment.repository.network.HTTPClient

import com.knitterassignment.BuildConfig
import com.knitterassignment.util.Constants
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HTTPClient {
    companion object {
        private fun getHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(HTTPInterceptor())
                .addInterceptor(Interceptor.invoke {
                    val request = it.request()

                    val response = it.proceed(request)

                    if(response.code == 404) {
                        Logger.d("Error in fetching")
                        return@invoke response
                    }
                    return@invoke response
                })
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()
        }

        fun getNetworkService(): Retrofit {
            return Retrofit.Builder()
                .client(getHttpClient())
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}