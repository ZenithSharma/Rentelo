package com.example.rentelo.dashboard.nearby

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NearByViewModel : ViewModel() {

    val nearByRent = MutableLiveData<Result<List<NearBy>>>()
}
