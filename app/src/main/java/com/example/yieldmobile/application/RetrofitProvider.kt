package com.example.yieldmobile.application

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitProvider {
    companion object {
        @JvmStatic
        fun create(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("http://10.0.2.2")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(OkHttpProvider.create())
                .build()
        }
    }
}