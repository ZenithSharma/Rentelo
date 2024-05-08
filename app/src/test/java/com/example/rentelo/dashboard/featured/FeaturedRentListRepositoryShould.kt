package com.example.rentelo.dashboard.featured

import com.example.rentelo.dashboard.RemoteDataSource
import com.example.rentelo.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FeaturedRentListRepositoryShould : BaseUnitTest() {

    private val service: RemoteDataSource = mock()
    private val featuredRentList = mock<List<FeaturedRent>>()
    private val expected = Result.success(featuredRentList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getFeaturedListFromRemoteDataSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getFeaturedRentList()
        verify(service, times(1)).getFeaturedRentListFromAPI()
    }

    @Test
    fun emitFeaturedRentListFromRemoteDataSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getFeaturedRentList()
        assertEquals(featuredRentList, repository.getFeaturedRentList().first().getOrNull())
    }


    @Test
    fun emitErrorWhenReceivedError() = runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getFeaturedRentList().first().exceptionOrNull())
    }

    private fun mockSuccessfulCase(): FeaturedRentListRepository {
        whenever(service.getFeaturedRentListFromAPI()).thenReturn(flow {
            emit(expected)
        })
        return FeaturedRentListRepository(service)
    }

    private fun mockFailureCase(): FeaturedRentListRepository {
        whenever(service.getFeaturedRentListFromAPI()).thenReturn(flow {
            emit(Result.failure(exception))
        })
        return FeaturedRentListRepository(service)
    }
}