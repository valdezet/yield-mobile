package com.example.yieldmobile.application

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class OkHttpProviderModule() {
        @Provides
        fun create(): OkHttpClient {
            val httpBuilder = OkHttpClient.Builder()
            httpBuilder.addInterceptor { chain ->
                val requestBuilder =
                    chain.request().newBuilder().addHeader("Accept", "Application/json")
                chain.proceed(requestBuilder.build())
            }

            return httpBuilder.build()
        }
}