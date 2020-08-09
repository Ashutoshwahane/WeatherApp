package com.cybernerd.weatherapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cybernerd.weatherapp.model.WeatherResponse
import com.cybernerd.weatherapp.repository.DetailActivityRepository

class DetailActivityViewModel(application: Application): AndroidViewModel(application) {
    private val repository = DetailActivityRepository(application)

    var showProgress : LiveData<Boolean>
    var response : LiveData<WeatherResponse>

    init {
        showProgress = repository.showProgress
        response = repository.response
    }

    fun getWeather(woeid: Int){
        repository.getWeather(woeid)
    }
}