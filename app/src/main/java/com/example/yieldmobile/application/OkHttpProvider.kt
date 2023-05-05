package com.example.yieldmobile.application

import okhttp3.OkHttpClient

class OkHttpProvider {
    companion object {
        @JvmStatic
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
}