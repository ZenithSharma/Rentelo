package com.example.rentelo.dashboard.collection

import com.example.rentelo.dashboard.ApiService
import com.example.rentelo.dashboard.RemoteDataSource
import com.example.rentelo.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CollectionRemoteDataSourceShould : BaseUnitTest() {
    private val api: ApiService = mock()
    private val expected = mock<List<CollectionRent>>()

    @Test
    fun getCollectionListFromAPI() = runTest {
        val remoteDataSource = RemoteDataSource(api)
        remoteDataSource.getCollectionRentListFromAPI().first()
        verify(api, times(1)).getCollectionList()
    }

    @Test
    fun convertValuesToFlowAndEmitThem() = runTest {
        val remoteDataSource = mockSuccessfulCase()
        assertEquals(
            Result.success(expected), remoteDataSource.getCollectionRentListFromAPI().first()
        )
    }

    @Test
    fun emitErrorWhenNetworkFails() = runTest {
        whenever(api.getCollectionList()).thenThrow(RuntimeException("Something went wrong"))
        val remoteDataSource = RemoteDataSource(api)
        assertEquals(
            "Something went wrong",
            remoteDataSource.getCollectionRentListFromAPI().first().exceptionOrNull()?.message
        )
    }

    private fun mockSuccessfulCase(): RemoteDataSource {
        runBlocking {
            whenever(api.getCollectionList()).thenReturn(expected)
        }
        return RemoteDataSource(api)
    }
}