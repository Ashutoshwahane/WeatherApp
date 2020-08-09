package com.cybernerd.weatherapp.network

import com.cybernerd.weatherapp.model.Location
import com.cybernerd.weatherapp.model.WeatherResponse
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val BASE_URL = "https://www.metaweather.com/api/location/"

interface WeatherInterface {

    @GET("search?")
    fun getLocation(@Query("query")searchString: String): Call<List<Location>>

    @GET("{woeid}")
    fun getWeather(@Path("woeid") woeid: Int) : Call<WeatherResponse>

}

