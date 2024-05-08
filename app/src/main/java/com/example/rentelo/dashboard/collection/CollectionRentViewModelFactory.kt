package com.example.rentelo.dashboard.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class CollectionRentViewModelFactory @Inject constructor(private val repository: CollectionRentRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CollectionRentViewModel(repository) as T
    }
}
