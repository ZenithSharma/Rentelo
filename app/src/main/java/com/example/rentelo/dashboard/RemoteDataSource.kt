package com.example.rentelo.dashboard

import com.example.rentelo.dashboard.collection.CollectionRent
import com.example.rentelo.dashboard.featured.FeaturedRent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getFeaturedRentListFromAPI(): Flow<Result<List<FeaturedRent>>> {
        return flow {
            emit(Result.success(apiService.getFeaturedRentList()))
        }
    }

    fun getCollectionRentListFromAPI(): Flow<Result<List<CollectionRent>>> {
        TODO("Not yet implemented")
    }
}
