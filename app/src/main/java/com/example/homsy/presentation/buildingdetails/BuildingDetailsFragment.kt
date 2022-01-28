package com.example.homsy.presentation.buildingdetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.homsy.databinding.FragmentBuildingDetailsBinding
import com.example.homsy.pxFromDp
import com.example.homsy.setTopMargin

class BuildingDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBuildingDetailsBinding

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
        binding.overviewTextView.isSelected = true
        binding.overviewDot.visibility = View.VISIBLE

        binding.reviewsTextView.isSelected = false
        binding.reviewsDot.visibility = View.INVISIBLE
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
        private const val BACK_BUTTON_TOP_MARGIN_DP = 26
        private const val FAVORITE_BUTTON_TOP_MARGIN_DP = 26
    }
}