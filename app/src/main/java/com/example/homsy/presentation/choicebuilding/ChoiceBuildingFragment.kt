package com.example.homsy.presentation.choicebuilding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homsy.R
import com.example.homsy.databinding.FragmentChoiceBuildingBinding
import com.example.homsy.presentation.buildingdetails.BuildingDetailsFragment
import com.example.homsy.presentation.buildingdetails.BuildingDetailsFragment.Companion.BUILDING_ID
import com.example.homsy.presentation.choicebuilding.adapter.BuildingAdapter
import com.example.homsy.presentation.choicebuilding.adapter.CategoryAdapter
import com.example.homsy.pxFromDp
import com.example.homsy.setBottomMargin
import com.example.homsy.setTopMargin

class ChoiceBuildingFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBuildingBinding
    private val viewModel: ChoiceBuildingViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChoiceBuildingBinding.inflate(inflater, container, false)
        setupInsets(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter(viewModel::onCategoryClicked)
        val buildingAdapter = BuildingAdapter(::onBuildingClicked)

        binding.categoryList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
            addItemDecoration(
                MarginItemDecoration(
                    requireContext(),
                    CATEGORY_LIST_TOP_MARGIN_DP,
                    CATEGORY_LIST_INTERNAL_MARGIN_DP,
                    CATEGORY_LIST_BOTTOM_MARGIN_DP
                )
            )
        }
        binding.buildingList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = buildingAdapter
            addItemDecoration(
                MarginItemDecoration(
                    requireContext(),
                    BUILDING_LIST_TOP_MARGIN_DP,
                    BUILDING_LIST_INTERNAL_MARGIN_DP,
                    BUILDING_LIST_BOTTOM_MARGIN_DP
                )
            )
        }

        viewModel.categories.observe(this) {
            categoryAdapter.categories = it
        }
        viewModel.buildings.observe(this) {
            buildingAdapter.buildings = it
        }
    }

    private fun onBuildingClicked(buildingId: Int) {
        val fragment = BuildingDetailsFragment().apply {
            arguments = bundleOf(BUILDING_ID to buildingId)
        }
        requireActivity().supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment)
        }
    }

    private fun setupInsets(context: Context) {
        ViewCompat.setOnApplyWindowInsetsListener(binding.menuButton) { menuButton, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            menuButton.setTopMargin(context.pxFromDp(MENU_BUTTON_TOP_MARGIN_DP) + insets.top)
            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.userPhotoImageView) { userPhotoImageView, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            userPhotoImageView.setTopMargin(context.pxFromDp(USER_PHOTO_IMAGE_VIEW_TOP_MARGIN_DP) + insets.top)
            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.categoryList) { categoryList, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            categoryList.setBottomMargin(insets.bottom)
            windowInsets
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.buildingList) { buildingList, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            buildingList.setBottomMargin(insets.bottom)
            windowInsets
        }
    }

    companion object {
        private const val MENU_BUTTON_TOP_MARGIN_DP = 26
        private const val USER_PHOTO_IMAGE_VIEW_TOP_MARGIN_DP = 26
        private const val CATEGORY_LIST_TOP_MARGIN_DP = 26
        private const val CATEGORY_LIST_INTERNAL_MARGIN_DP = 20
        private const val CATEGORY_LIST_BOTTOM_MARGIN_DP = 26
        private const val BUILDING_LIST_TOP_MARGIN_DP = 26
        private const val BUILDING_LIST_INTERNAL_MARGIN_DP = 16
        private const val BUILDING_LIST_BOTTOM_MARGIN_DP = 26
    }
}