package com.example.rentelo.dashboard.nearby

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NearByRentListRepository @Inject constructor() {
    fun getNearByRentListFromRemoteDataSource(): Flow<Result<List<NearBy>>> {
        TODO("Not yet implemented")
    }
}
