package com.example.rentelo.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class FeaturedRentListViewModelFactory @Inject constructor(private val repository: FeaturedRentListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FeaturedRentListViewModel(repository) as T
    }
}
