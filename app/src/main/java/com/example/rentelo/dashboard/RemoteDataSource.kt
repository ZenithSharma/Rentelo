package com.example.rentelo.dashboard

import com.example.rentelo.dashboard.collection.CollectionRent
import com.example.rentelo.dashboard.featured.FeaturedRent
import com.example.rentelo.dashboard.nearby.NearBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getFeaturedRentListFromAPI(): Flow<Result<List<FeaturedRent>>> {
        return flow {
            emit(Result.success(apiService.getFeaturedRentList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

    fun getCollectionRentListFromAPI(): Flow<Result<List<CollectionRent>>> {
        return flow {
            emit(Result.success(apiService.getCollectionList()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }

    fun getNearByRentListFromAPI():Flow<Result<List<NearBy>>> {
        TODO("Not yet implemented")
    }
}
