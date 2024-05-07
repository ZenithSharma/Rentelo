package com.example.rentelo.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FeaturedRentListViewModel(private val repository: FeaturedRentListRepository) : ViewModel() {

    val featuredRentList = MutableLiveData<Result<List<FeaturedRent>>>()

    init {
        viewModelScope.launch {
            repository.getFeaturedRentList().collect(){
                featuredRentList.value = it
            }
        }
    }
}
