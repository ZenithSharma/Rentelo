package com.example.rentelo.dashboard.collection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class CollectionRentViewModel(private val repository: CollectionRentRepository) : ViewModel() {

    var collectionRentList = liveData {
        repository.getCollectionRentList().collect() {
            emit(it)
        }
    }
}
