package com.example.rentelo.dashboard.featured

data class FeaturedRent(
    val bed: Int,
    val breakfast: Int,
    val description: String,
    val id: Int,
    val image: String,
    val location: String,
    val price: Int,
    val shower: Int,
    val sofa: Int
)