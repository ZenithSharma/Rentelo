package com.example.rentelo.dashboard.collection


import com.example.rentelo.dashboard.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionRentRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getCollectionRentList(): Flow<Result<List<CollectionRent>>> {
        return remoteDataSource.getCollectionRentListFromAPI()
    }
}
