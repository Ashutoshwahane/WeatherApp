package com.cybernerd.weatherapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cybernerd.weatherapp.R
import com.cybernerd.weatherapp.model.Location
import com.cybernerd.weatherapp.view.DetailActivity
import kotlinx.android.synthetic.main.rv_location_child.view.*

class LocationAdapter(private val context: Context) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>(){

    private var list: List<Location> = ArrayList()

    fun setLocation(list: List<Location>){
        this.list = list
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_location_child, parent, false)
            )
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val name = view.tv_location_name!!
        val latLong = view.tv_lat_long!!
        val rootView = view.childRoot!!

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {

        holder.name.text = list[position].title
        holder.latLong.text = list[position].latt_long
        holder.rootView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("location",list[position].woeid)
            intent.putExtra("name",list[position].title)
            context.startActivity(intent)
        }
    }

}