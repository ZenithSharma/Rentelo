package com.example.rentelo

import com.example.rentelo.dashboard.FeaturedRent
import com.example.rentelo.dashboard.FeaturedRentListRepository
import com.example.rentelo.dashboard.FeaturedRentListViewModel
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
import petros.efthymiou.groovy.utils.captureValues
import petros.efthymiou.groovy.utils.getValueForTest

class FeaturedRentListViewModelShould : BaseUnitTest() {

    private val repository: FeaturedRentListRepository = mock()
    private val featuredRentList: List<FeaturedRent> = mock()
    private val expected = Result.success(featuredRentList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getFeaturedRentListFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.featuredRentList.getValueForTest()
        verify(repository, times(1)).getFeaturedRentList()
    }

    @Test
    fun emitFeaturedRentListFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.featuredRentList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivedError() {
        val viewModel = mockFailureCase()
        assertEquals(exception, viewModel.featuredRentList.getValueForTest()!!.exceptionOrNull())
    }

    @Test
    fun showSpinnerWhileLoadingData() {
        val viewModel = mockSuccessfulCase()
        viewModel.loader.captureValues {
            viewModel.featuredRentList.getValueForTest()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun closeSpinnerAfterDataLoaded() {
        val viewModel = mockFailureCase()
        viewModel.loader.captureValues {
            viewModel.featuredRentList.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    @Test
    fun closeSpinnerAfterError() {
        val viewModel = mockFailureCase()
        viewModel.loader.captureValues {
            viewModel.featuredRentList.getValueForTest()
            assertEquals(false, values.last())
        }
    }

    private fun mockSuccessfulCase(): FeaturedRentListViewModel {
        runBlocking {
            whenever(repository.getFeaturedRentList()).thenReturn(flow {
                emit(expected)
            })
        }
        return FeaturedRentListViewModel(repository)
    }

    private fun mockFailureCase(): FeaturedRentListViewModel {
        runBlocking {
            whenever(repository.getFeaturedRentList()).thenReturn(flow {
                emit(Result.failure(exception))
            })
        }
        return FeaturedRentListViewModel(repository)
    }
}