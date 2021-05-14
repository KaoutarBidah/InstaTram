package com.example.instatramtest.ui.main

import android.app.Application
import androidx.lifecycle.*
import com.example.instatramtest.data.StationRepository

class PageViewModel(val app: Application) : AndroidViewModel(app) {
    private val dataRepo = StationRepository(app)
    val StationData = dataRepo.stationData


    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        "Hello world from section: $it"

    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}