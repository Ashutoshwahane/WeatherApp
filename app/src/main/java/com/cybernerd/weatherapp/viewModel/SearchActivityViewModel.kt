package com.cybernerd.weatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.weatherapp.model.Location
import com.cybernerd.weatherapp.repository.SearchActivityRepository

class SearchActivityViewModel(application: Application): AndroidViewModel(application) {

   private val repository = SearchActivityRepository(application)
    val showProgress : LiveData<Boolean>
    val locationList : LiveData<List<Location>>

    init {
        this.showProgress = repository.showProgress
        this.locationList = repository.locationList
    }

    fun changeProgress(){
        repository.changeState()
    }

    fun searchLocation(searchString: String){
        repository.searchLocation(searchString)
    }

}