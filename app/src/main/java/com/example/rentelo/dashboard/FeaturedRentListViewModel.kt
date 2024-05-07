package com.example.rentelo.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FeaturedRentListViewModel: ViewModel() {

    val featuredRentList = MutableLiveData<List<FeaturedRent>>()
}
