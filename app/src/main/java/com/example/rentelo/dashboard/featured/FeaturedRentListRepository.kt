package com.example.rentelo.dashboard.featured

import com.example.rentelo.dashboard.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeaturedRentListRepository @Inject constructor(private val service: RemoteDataSource) {
    suspend fun getFeaturedRentList(): Flow<Result<List<FeaturedRent>>> {
       return service.getFeaturedRentListFromAPI()
    }
}