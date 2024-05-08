package com.example.rentelo.dashboard

import com.example.rentelo.dashboard.featured.FeaturedRent
import retrofit2.http.GET


interface ApiService {
    @GET("featured-rent")
    suspend fun getFeaturedRentList(): List<FeaturedRent>
}
