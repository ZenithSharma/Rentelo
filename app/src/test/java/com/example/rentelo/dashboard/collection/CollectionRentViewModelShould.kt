package com.example.rentelo.dashboard.collection

import com.example.rentelo.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

class CollectionRentViewModelShould : BaseUnitTest() {
    private val repository: CollectionRentRepository = mock()
    private val collectionRent = mock<List<CollectionRent>>()
    private val expected = Result.success(collectionRent)
    private val exception = RuntimeException("Something went wrong")





    @Test
    fun getCollectionListFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.collectionRentList.getValueForTest()
        verify(repository, times(1)).getCollectionRentList()
    }

    @Test
    fun emitCollectionRentListFromRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        assertEquals(expected, viewModel.collectionRentList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivedError() = runTest {
        val viewModel = mockFailureCase()
        assertEquals(exception, viewModel.collectionRentList.getValueForTest()!!.exceptionOrNull())
    }

    private suspend fun mockSuccessfulCase(): CollectionRentViewModel {
        whenever(repository.getCollectionRentList()).thenReturn(flow {
            emit(expected)
        })
        return CollectionRentViewModel(repository)
    }

    private suspend fun mockFailureCase(): CollectionRentViewModel {
        whenever(repository.getCollectionRentList()).thenReturn(flow {
            emit(Result.failure(exception))
        })

        return CollectionRentViewModel(repository)
    }
}