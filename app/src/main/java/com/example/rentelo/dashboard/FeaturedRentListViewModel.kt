package com.example.rentelo.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach

class FeaturedRentListViewModel(private val repository: FeaturedRentListRepository) : ViewModel() {

    val loader = MutableLiveData<Boolean>()
    val featuredRentList = liveData {
        loader.postValue(true)
        emitSource(repository.getFeaturedRentList().onEach {
            loader.postValue(false)
        }.asLiveData())
    }
}
