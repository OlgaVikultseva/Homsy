package com.example.homsy.presentation.buildingdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homsy.R
import com.example.homsy.databinding.FragmentBuildingDetailsBinding
import com.example.homsy.presentation.choicebuilding.adapter.BuildingItem
import com.example.homsy.setBottomMargin
import com.example.homsy.setTopMargin

class BuildingDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBuildingDetailsBinding
    private lateinit var viewModelFactory: BuildingDetailsViewModelFactory
    private val viewModel: BuildingDetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            onBackButtonClicked()
        }

        val buildingId = arguments?.getInt(BUILDING_ID, -1)
        if (buildingId != null && buildingId != -1) {
            viewModelFactory = BuildingDetailsViewModelFactory(buildingId)
        } else {
            throw IllegalArgumentException("Invalid building id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBuildingDetailsBinding.inflate(inflater, container, false)
        setupInsets()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.building.observe(this) {
            if (it != null) {
                showData(it)
            }
        }

        with(binding) {
            backButton.setOnClickListener { onBackButtonClicked() }
            overviewTextView.isSelected = true
            overviewDot.visibility = View.VISIBLE
            reviewsTextView.isSelected = false
            reviewsDot.visibility = View.INVISIBLE
        }
    }

    private fun showData(building: BuildingItem) {
        with(binding) {
            headlineTextView.text = building.buildingType
            locationTextView.text = building.location
            buildingTypeTextView.text = building.buildingType
            contentTextView.text = building.description
            priceTextView.text = getString(R.string.price_placeholder, building.price)
            buildingImageView.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    building.resId
                )
            )
        }
    }

    private fun onBackButtonClicked() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.headerView) { headerView, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            headerView.setTopMargin(insets.top)
            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.footerView) { footerView, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            footerView.setBottomMargin(insets.bottom)
            windowInsets
        }
    }

    companion object {
        const val BUILDING_ID = "BUILDING_ID"
    }
}