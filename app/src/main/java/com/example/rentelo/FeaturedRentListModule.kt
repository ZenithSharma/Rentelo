package com.example.rentelo

import com.example.rentelo.dashboard.ApiService
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)
@Module
@InstallIn(FragmentComponent::class)
class FeaturedRentListModule {

    @Provides
    fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.mockfly.dev/mocks/75e3fd7b-3836-4ab0-a013-911ca1123f00/").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()


    }


}