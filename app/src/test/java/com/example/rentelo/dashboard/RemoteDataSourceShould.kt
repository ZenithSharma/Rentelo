package com.example.rentelo.dashboard

import com.example.rentelo.dashboard.featured.FeaturedRent
import com.example.rentelo.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RemoteDataSourceShould : BaseUnitTest() {

    private val api: ApiService = mock()
    private val featuredRentList: List<FeaturedRent> = mock()


    @Test
    fun getFeaturedListFromAPI() = runTest {
        val remoteDataSource = RemoteDataSource(api)
        remoteDataSource.getFeaturedRentListFromAPI().first()
        verify(api, times(1)).getFeaturedRentList()
    }

    @Test
    fun convertValuesToFlowAndEmitThem() = runTest {
        whenever(api.getFeaturedRentList()).thenReturn(featuredRentList)
        val remoteDataSource = RemoteDataSource(api)
        assertEquals(Result.success(featuredRentList),remoteDataSource.getFeaturedRentListFromAPI().first())
    }

    @Test
    fun emitErrorResultWhenNetworkFails() = runTest {
        val remoteDataSource = mockFailureCase()
        assertEquals(
            "Something went wrong",
            remoteDataSource.getFeaturedRentListFromAPI().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockFailureCase(): RemoteDataSource {
        whenever(api.getFeaturedRentList()).thenThrow(RuntimeException("Something went wrong"))
        return RemoteDataSource(api)
    }
}