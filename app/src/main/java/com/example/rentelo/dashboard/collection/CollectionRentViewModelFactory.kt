package com.example.rentelo.dashboard.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CollectionRentViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CollectionRentViewModel() as T
    }
}
