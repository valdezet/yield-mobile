package com.example.yieldmobile.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RetrofitApiProviderModule {

    @Provides
    fun provideAuthApi(retrofit: Retrofit): AuthRetrofitApi {
        return retrofit.create(AuthRetrofitApi::class.java)
    }

    @Provides
    fun provideTaskApi(retrofit: Retrofit): TaskRetrofitApi {
        return retrofit.create(TaskRetrofitApi::class.java)
    }
}