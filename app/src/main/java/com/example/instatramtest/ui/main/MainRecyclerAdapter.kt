package com.example.instatramtest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.instatramtest.R
import com.example.instatramtest.data.Station

class MainRecyclerAdapter (val context: Context, var clickListner: OnStationItemClickListner,
                           val stations: List<Station>): RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    override fun getItemCount() = stations.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.station_grid, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        val station = stations[position]
        with(holder) {
            btn.let {
                it.text = station.name
                it.setOnClickListener{
                    clickListner.onItemClick(station,position)
                }

            }


        }
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val btn: Button = itemView.findViewById(R.id.btn)
    }
}

interface OnStationItemClickListner{
    fun onItemClick(item: Station, position: Int)
}

