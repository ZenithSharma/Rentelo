package com.example.rentelo.dashboard.nearby

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class NearByViewModelFactory @Inject constructor(private val repository: NearByRentListRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NearByViewModel(repository) as T
    }
}
