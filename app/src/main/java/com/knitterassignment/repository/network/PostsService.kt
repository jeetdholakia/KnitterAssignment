package com.knitterassignment.repository.network

import com.knitterassignment.BuildConfig
import com.knitterassignment.util.Constants.Companion.baseUrl
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object PostsService {

    private val myHTTPInterceptor = OkHttpClient().newBuilder()
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


    fun getPostsService(): PostApi {
        return Retrofit.Builder()
            .client(myHTTPInterceptor)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }
}