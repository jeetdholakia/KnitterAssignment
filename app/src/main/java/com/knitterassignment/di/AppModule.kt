package dev.jeetdholakia.androidmvvmdagger.di

import com.knitterassignment.BuildConfig
import com.knitterassignment.repository.network.HTTPClient.HTTPInterceptor
import com.knitterassignment.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideHttpClient(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .addInterceptor(HTTPInterceptor())
                .addInterceptor(Interceptor.invoke {
                    val request = it.request()
                    val response = it.proceed(request)
                    if (response.code == 404) {
                        return@invoke response
                    }
                    return@invoke response
                })
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()
        }

        @Singleton
        @Provides
        fun provideRetrofitInstance(httpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .client(httpClient)
                .baseUrl(Constants.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}