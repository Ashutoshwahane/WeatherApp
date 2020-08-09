package com.cybernerd.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cybernerd.weatherapp.R
import com.cybernerd.weatherapp.adapter.LocationAdapter
import com.cybernerd.weatherapp.viewModel.SearchActivityViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private lateinit var searchActivityViewModel: SearchActivityViewModel
    private lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchActivityViewModel = ViewModelProvider(this).get(SearchActivityViewModel::class.java)

        iv_search.setOnClickListener {
            if (et_search.text!!.isNotEmpty()) {
                searchActivityViewModel.searchLocation(et_search.text.toString())
            }

        }

        searchActivityViewModel.showProgress.observe(this, Observer {
            if (it){
                search_progressbar.visibility = VISIBLE
            }else{
                search_progressbar.visibility = GONE
            }
        })


        searchActivityViewModel.locationList.observe(this, Observer {
            locationAdapter.setLocation(it)
        })
        locationAdapter = LocationAdapter(this)
        recycleview.adapter = locationAdapter




    }
}
