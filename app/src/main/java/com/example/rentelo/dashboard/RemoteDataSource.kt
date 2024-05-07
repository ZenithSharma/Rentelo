package com.example.rentelo.dashboard

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor() {
    fun getFeaturedRentListFromAPI(): Flow<Result<List<FeaturedRent>>> {
        return flow {

        }
    }
}
