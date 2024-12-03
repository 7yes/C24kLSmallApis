package com.jesse.c24klsmallapis.di

import com.jesse.c24klsmallapis.data.SampleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofil(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bobsburgers-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun providesSampleApi(retrofit: Retrofit): SampleApi {
        return retrofit.create(SampleApi::class.java)
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }




}