package com.example.homsy.presentation.buildingdetails

import android.content.Context
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
import com.example.homsy.databinding.FragmentBuildingDetailsBinding
import com.example.homsy.presentation.choicebuilding.adapter.BuildingItem
import com.example.homsy.pxFromDp
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
        setupInsets(requireContext())
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

    private fun setupInsets(context: Context) {
        ViewCompat.setOnApplyWindowInsetsListener(binding.backButton) { backButton, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            backButton.setTopMargin(context.pxFromDp(BACK_BUTTON_TOP_MARGIN_DP) + insets.top)
            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.favoriteButton) { favoriteButton, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            favoriteButton.setTopMargin(context.pxFromDp(FAVORITE_BUTTON_TOP_MARGIN_DP) + insets.top)
            windowInsets
        }
    }

    companion object {
        const val BUILDING_ID = "BUILDING_ID"
        private const val BACK_BUTTON_TOP_MARGIN_DP = 26
        private const val FAVORITE_BUTTON_TOP_MARGIN_DP = 26
    }
}