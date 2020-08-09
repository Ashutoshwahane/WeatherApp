package com.cybernerd.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cybernerd.weatherapp.R
import com.cybernerd.weatherapp.viewModel.DetailActivityViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var detailActivityViewModel: DetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailActivityViewModel = ViewModelProvider(this).get(DetailActivityViewModel::class.java)


        if (intent.hasExtra("name")){
            tv_location.text = intent.getStringExtra("name")
        }

        if (intent.hasExtra("location")){
            // network call
            val location = intent.getIntExtra("location",0)
            if (location > 0){
                detailActivityViewModel.getWeather(location)
            }
        }

        detailActivityViewModel.showProgress.observe(this, Observer {
            if (it){
                detail_progressbar.visibility = VISIBLE
            }else{
                detail_progressbar.visibility = GONE
            }
        })

        detailActivityViewModel.response.observe(this, Observer {
            if (it != null){
                tv_temp.text = it.consolidated_weather[0].the_temp.toString()
            }
        })

    }
}
