package com.example.rentelo.dashboard

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class FeaturedRentListModule {

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("http://192.168.101.8:3000/").client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}