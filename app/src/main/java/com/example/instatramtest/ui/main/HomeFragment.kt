package com.example.instatramtest.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instatramtest.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.instatramtest.data.Station
import com.example.instatramtest.img.img_activity


@Suppress("DEPRECATION")
class HomeFragment : Fragment() ,OnStationItemClickListner{
    private lateinit var viewModel: PageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)

        viewModel.StationData.observe(this, Observer
        {
            val adapter = MainRecyclerAdapter(requireContext(),this ,it)
            recyclerView.adapter = adapter


        })
        return view
    }
    override fun onItemClick(item: Station, position: Int) {
        val intent = Intent(context,img_activity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)

    }

    companion object {

        @JvmStatic
        fun newInstance(
        ) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}