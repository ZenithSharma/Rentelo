package com.example.rentelo.dashboard.nearby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NearByViewModel(private val repository: NearByRentListRepository) : ViewModel() {

    val nearByRent = liveData {
        emitSource(repository.getNearByRentListFromRemoteDataSource().asLiveData())
    }
}
