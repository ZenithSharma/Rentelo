package com.example.rentelo.dashboard.collection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CollectionRentViewModel : ViewModel() {

    val collectionRentList = MutableLiveData<CollectionRent>()
}
