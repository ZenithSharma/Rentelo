package com.example.rentelo.dashboard

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getFeaturedRentListFromAPI(): Flow<Result<List<FeaturedRent>>> {
        return flow {
            emit(Result.success(apiService.getFeaturedRentList()))
        }
    }
}
