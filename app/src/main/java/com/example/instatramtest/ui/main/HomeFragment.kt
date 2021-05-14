package com.example.instatramtest.ui.main

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

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() ,OnStationItemClickListner{
    private lateinit var viewModel: PageViewModel

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        viewModel = ViewModelProviders.of(this).get(PageViewModel::class.java)

        viewModel.StationData.observe(viewLifecycleOwner, Observer
        {
            val adapter = MainRecyclerAdapter(requireContext(),this ,it)
            recyclerView.adapter = adapter


        })
        return view
    }
    override fun onItemClick(item: Station, position: Int) {
        val intent = Intent(context,image_activity::class.java)
        intent.putExtra("name", item.name)
        startActivity(intent)

    }

    companion object {

        @JvmStatic
        fun newInstance(
        ) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}