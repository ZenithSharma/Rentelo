package com.example.rentelo.dashboard

import retrofit2.http.GET


interface ApiService {
    @GET("featured-rent")
    suspend fun getFeaturedRentList(): List<FeaturedRent>
}
