package com.example.homsy.presentation.buildingdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.homsy.R

class BuildingDetailsFragment : Fragment() {

    private var buildingId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            buildingId = it.getInt(BUILDING_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_building_details, container, false)

    companion object {
        fun newInstance(buildingId: Int) =
            BuildingDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUILDING_ID, buildingId)
                }
            }
        private const val BUILDING_ID = "BUILDING_ID"
    }
}