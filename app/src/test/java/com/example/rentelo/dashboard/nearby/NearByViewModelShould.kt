package com.example.rentelo.dashboard.nearby

import com.example.rentelo.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

class NearByViewModelShould : BaseUnitTest() {

    private val repository: NearByRentListRepository = mock()
    private val nearByRentList = mock<List<NearBy>>()
    private val expected = Result.success(nearByRentList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getNearByRentListFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.nearByRent.getValueForTest()
        verify(repository, times(1)).getNearByRentListFromRemoteDataSource()
    }

    @Test
    fun emitNearByRentFromRepository() {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.nearByRent.getValueForTest())
    }

    @Test
    fun emitErrorWhileReceivedError() {
        val viewModel = mockFailureCase()
        assertEquals(exception, viewModel.nearByRent.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockSuccessfulCase(): NearByViewModel {
        runBlocking {
            whenever(repository.getNearByRentListFromRemoteDataSource()).thenReturn(flow {
                emit(expected)
            })
        }
        return NearByViewModel(repository)
    }

    private fun mockFailureCase(): NearByViewModel {
        runBlocking {
            whenever(repository.getNearByRentListFromRemoteDataSource()).thenReturn(flow {
                emit(
                    Result.failure(
                        exception
                    )
                )
            })
        }
        return NearByViewModel(repository)
    }
}