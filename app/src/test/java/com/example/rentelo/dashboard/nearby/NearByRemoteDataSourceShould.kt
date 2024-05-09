package com.example.rentelo.dashboard.nearby

import com.example.rentelo.dashboard.ApiService
import com.example.rentelo.dashboard.RemoteDataSource
import com.example.rentelo.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NearByRemoteDataSourceShould : BaseUnitTest() {
    private val api: ApiService = mock()
    private val nearByRentList = mock<List<NearBy>>()

    @Test
    fun getNearByRentFromAPI() = runTest {
        val remoteDataSource = RemoteDataSource(api)
        remoteDataSource.getNearByRentListFromAPI().first()
        verify(api, times(1)).getNearByRentList()
    }

    @Test
    fun convertDataToFlowAndEmitThem() = runTest {
        whenever(api.getNearByRentList()).thenReturn(nearByRentList)
        val remoteDataSource = RemoteDataSource(api)
        assertEquals(
            Result.success(nearByRentList), remoteDataSource.getNearByRentListFromAPI().first()
        )
    }

    @Test
    fun emitErrorWhenReceivedError() = runTest {
        val remoteDataSource = mockErrorCase()
        assertEquals(
            "Something went wrong",
            remoteDataSource.getNearByRentListFromAPI().first().exceptionOrNull()?.message
        )
    }

    private suspend fun mockErrorCase(): RemoteDataSource {
        whenever(api.getNearByRentList()).thenThrow(RuntimeException("Something went wrong"))
        return RemoteDataSource(api)
    }
}