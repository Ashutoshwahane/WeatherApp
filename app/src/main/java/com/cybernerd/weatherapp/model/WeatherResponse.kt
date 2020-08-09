package com.cybernerd.weatherapp.model

data class WeatherResponse(
    val consolidated_weather: List<ConsolidatedWeather>,
    val title: String,
    val time: String,
    val woeid: Int



//    val latt_long: String,
//    val location_type: String,
//    val parent: Parent,
//    val sources: List<Source>,
//    val sun_rise: String,
//    val sun_set: String,
//    val timezone: String,
//    val timezone_name: String,

)