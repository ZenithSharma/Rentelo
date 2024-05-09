package com.example.rentelo.dashboard

import com.example.rentelo.dashboard.collection.CollectionRent
import com.example.rentelo.dashboard.featured.FeaturedRent
import com.example.rentelo.dashboard.nearby.NearBy
import retrofit2.http.GET


interface ApiService {
    @GET("featured-rent")
    suspend fun getFeaturedRentList(): List<FeaturedRent>
    @GET("collection")
    suspend fun getCollectionList(): List<CollectionRent>
    @GET("near-by")
    suspend fun getNearByRentList(): List<NearBy>
}
