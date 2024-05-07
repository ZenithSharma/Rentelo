package com.example.rentelo.dashboard

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeaturedRentListRepository @Inject constructor(private val service: RemoteDataSource) {
    suspend fun getFeaturedRentList(): Flow<Result<List<FeaturedRent>>> {
       return service.getFeaturedRentListFromAPI()
    }
}