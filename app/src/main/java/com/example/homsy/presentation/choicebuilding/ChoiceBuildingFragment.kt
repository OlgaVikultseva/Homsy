package com.example.homsy.presentation.choicebuilding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homsy.data.DataSource
import com.example.homsy.databinding.FragmentChoiceBuildingBinding
import com.example.homsy.presentation.choicebuilding.adapter.BuildingAdapter
import com.example.homsy.presentation.choicebuilding.adapter.CategoryAdapter

class ChoiceBuildingFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBuildingBinding
    private val dataSource = DataSource()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChoiceBuildingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(::onCategoryClicked)
        val buildingAdapter = BuildingAdapter(::onBuildingClicked)

        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
        binding.buildingList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = buildingAdapter
        }

        categoryAdapter.categories = dataSource.getCategoryList()
        buildingAdapter.buildings = dataSource.getBuildingList()
    }

    private fun onCategoryClicked(id: Int) {
        Toast.makeText(context, "Selected category id: $id", Toast.LENGTH_SHORT).show()
    }

    private fun onBuildingClicked(id: Int) {
        Toast.makeText(context, "Selected building id: $id", Toast.LENGTH_SHORT).show()
    }
}