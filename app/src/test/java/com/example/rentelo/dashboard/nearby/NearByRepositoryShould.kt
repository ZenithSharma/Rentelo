package com.example.rentelo.dashboard.nearby

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

class NearByRepositoryShould : BaseUnitTest() {
    private val remoteDataSource: RemoteDataSource = mock()
    private val nearByRent = mock<List<NearBy>>()
    private val expected = Result.success(nearByRent)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getNearByRentFromRemoteDataSource() = runTest {
        val repository = NearByRentListRepository(remoteDataSource)
        repository.getNearByRentListFromRemoteDataSource()
        verify(remoteDataSource, times(1)).getNearByRentListFromAPI()
    }

    @Test
    fun emitNearByRentListFromRemoteDataSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getNearByRentListFromRemoteDataSource()
        assertEquals(
            nearByRent, repository.getNearByRentListFromRemoteDataSource().first().getOrNull()
        )
    }

    @Test
    fun emitErrorWhileReceivedError() = runTest {
        val repository = mockFailureCase()
        assertEquals(
            exception, repository.getNearByRentListFromRemoteDataSource().first().exceptionOrNull()
        )
    }

    private fun mockSuccessfulCase(): NearByRentListRepository {
        whenever(remoteDataSource.getNearByRentListFromAPI()).thenReturn(flow {
            emit(expected)
        })
        return NearByRentListRepository(remoteDataSource)
    }

    private fun mockFailureCase(): NearByRentListRepository {
        whenever(remoteDataSource.getNearByRentListFromAPI()).thenReturn(flow {
            emit(
                Result.failure(
                    exception
                )
            )
        })
        return NearByRentListRepository(remoteDataSource)
    }
}