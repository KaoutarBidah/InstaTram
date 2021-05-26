@file:Suppress("DEPRECATION")

package com.example.instatramtest.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.instatramtest.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Suppress("DEPRECATION")
class MapsFragment : Fragment() {

    private lateinit var viewModel: PageViewModel
    private lateinit var GoogleMap: GoogleMap

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)
        viewModel.StationData.observe(this, {
            for (station in it) {
                val sydney = LatLng(station.lat.toDouble(),station.lon.toDouble())
                GoogleMap.addMarker(MarkerOptions().position(sydney).title(station.name))
                GoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            }

        })

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap -> GoogleMap =googleMap  }


    }
    companion object {

        fun newInstance() =
            MapsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}