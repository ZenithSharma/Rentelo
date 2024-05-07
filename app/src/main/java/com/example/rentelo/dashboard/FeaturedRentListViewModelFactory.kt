package com.example.rentelo.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class FeaturedRentListViewModelFactory @Inject constructor() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeaturedRentListViewModel() as T
    }
}
