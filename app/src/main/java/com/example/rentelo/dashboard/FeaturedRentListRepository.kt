package com.example.rentelo.dashboard

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeaturedRentListRepository @Inject constructor() {
   suspend fun getFeaturedRentList():Flow<Result<List<FeaturedRent>>> {
        TODO("Not yet implemented")
    }
}
