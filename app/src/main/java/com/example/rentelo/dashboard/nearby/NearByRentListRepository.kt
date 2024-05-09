package com.example.rentelo.dashboard.nearby

import com.example.rentelo.dashboard.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NearByRentListRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun getNearByRentListFromRemoteDataSource(): Flow<Result<List<NearBy>>> =
        remoteDataSource.getNearByRentListFromAPI()
}
