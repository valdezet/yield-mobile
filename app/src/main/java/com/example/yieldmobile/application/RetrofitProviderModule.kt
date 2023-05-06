package com.example.yieldmobile.application

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RetrofitProviderModule {
    @Provides
    fun create(okHttpClient:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}