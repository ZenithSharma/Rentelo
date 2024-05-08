package com.example.rentelo.dashboard.collection

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

class CollectionRepositoryShould : BaseUnitTest() {

    private val remoteDataSource: RemoteDataSource = mock()
    private val collectionList = mock<List<CollectionRent>>()
    private val expected = Result.success(collectionList)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getCollectionListFromRemoteDataSource() = runTest {
        val repository = CollectionRentRepository(remoteDataSource)
        repository.getCollectionRentList()
        verify(remoteDataSource, times(1)).getCollectionRentListFromAPI()
    }

    @Test
    fun emitCollectionListFromRemoteDataSource() = runTest {
        val repository = mockSuccessfulCase()
        repository.getCollectionRentList()
        assertEquals(collectionList, repository.getCollectionRentList().first().getOrNull())
    }

    @Test
    fun emitErrorWhenReceivedError() = runTest {
        val repository = mockFailureCase()
        assertEquals(exception, repository.getCollectionRentList().first().exceptionOrNull())
    }

    private fun mockFailureCase(): CollectionRentRepository {
        whenever(remoteDataSource.getCollectionRentListFromAPI()).thenReturn(flow {
            emit(Result.failure(exception))
        })
        return CollectionRentRepository(remoteDataSource)
    }

    private fun mockSuccessfulCase(): CollectionRentRepository {
        whenever(remoteDataSource.getCollectionRentListFromAPI()).thenReturn(flow {
            emit(expected)
        })
        return CollectionRentRepository(remoteDataSource)
    }
}