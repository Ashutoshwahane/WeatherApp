package com.cybernerd.weatherapp.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.cybernerd.weatherapp.model.Location
import com.cybernerd.weatherapp.network.BASE_URL
import com.cybernerd.weatherapp.network.WeatherInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivityRepository(val application: Application){

    val showProgress = MutableLiveData<Boolean>()

    val locationList = MutableLiveData<List<Location>>()


    fun changeState(){
        showProgress.value = !(showProgress.value != null && showProgress.value!!)
    }

    fun searchLocation(searchString: String){
        showProgress.value = true
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherInterface::class.java)

        service.getLocation(searchString).enqueue(object  : Callback<List<Location>>{
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                Toast.makeText(application, "Error: ${t.message}",Toast.LENGTH_SHORT).show()
                showProgress.value = false


            }

            override fun onResponse(
                call: Call<List<Location>>,
                response: Response<List<Location>>
            ) {
                Log.d("debug","Response : ${Gson().toJson(response.body())}")
                locationList.value = response.body()
                showProgress.value = false
            }

        })
    }






}