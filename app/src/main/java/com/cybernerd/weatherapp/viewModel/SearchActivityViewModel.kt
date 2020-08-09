package com.cybernerd.weatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.cybernerd.weatherapp.repository.SearchActivityRepository

class SearchActivityViewModel(application: Application): AndroidViewModel(application) {

    val repository = SearchActivityRepository(application)
    val showProgress : LiveData<Boolean>

    init {
        this.showProgress = repository.showProgress
    }

    fun changeProgress(){
        repository.changeState()
    }

}