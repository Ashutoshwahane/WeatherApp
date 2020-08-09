package com.cybernerd.weatherapp.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.cybernerd.weatherapp.model.WeatherResponse
import com.cybernerd.weatherapp.network.BASE_URL
import com.cybernerd.weatherapp.network.WeatherInterface
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivityRepository(val application: Application) {

    val showProgress = MutableLiveData<Boolean>()
    val response = MutableLiveData<WeatherResponse>()

    var lastRequestTime : Long = -1

    fun getWeather(woeid: Int){


        if ((System.currentTimeMillis() - lastRequestTime) < 10000){
            return
        }

        showProgress.value = true


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherInterface::class.java)


        service.getWeather(woeid).enqueue(object : Callback<WeatherResponse>{
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application, "Error: ${t.message}", Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(
                call: Call<WeatherResponse>,
                resp: Response<WeatherResponse>
            ) {
                response.value = resp.body()
                lastRequestTime = System.currentTimeMillis()
                showProgress.value = false


            }

        })

    }

}