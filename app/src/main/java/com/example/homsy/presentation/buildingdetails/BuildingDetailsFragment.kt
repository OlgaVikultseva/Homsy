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
import com.google.android.material.imageview.ShapeableImageView

class BuildingDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBuildingDetailsBinding
    private lateinit var photosImageViewList: List<ShapeableImageView>
    private lateinit var viewModelFactory: BuildingDetailsViewModelFactory
    private val viewModel: BuildingDetailsViewModel by viewModels { viewModelFactory }

    private var shortAnimationDuration: Int = 0
    private var mediumAnimationDuration: Int = 0
    private var otherPhotoLayoutIsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        mediumAnimationDuration = resources.getInteger(android.R.integer.config_mediumAnimTime)

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
            photosButton.setOnClickListener { onPhotosButtonClicked() }

            photosLayout.visibility = View.GONE

            photosImageViewList = listOf(firstPhotoImageView, secondPhotoImageView, thirdPhotoImageView)
            photosImageViewList.forEach {
                it.visibility = View.GONE
            }

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

            mainPhotoImageView.setImageDrawable(
                AppCompatResources.getDrawable(
                    binding.root.context,
                    building.resIds.first()
                )
            )

            val numberOfPhotos = building.resIds.take(MAX_NUMBER_OF_PHOTOS_TO_DISPLAY).size

            for (i in 0 until numberOfPhotos) {
                photosImageViewList[i].apply {
                    visibility = View.VISIBLE
                    setImageDrawable(
                        AppCompatResources.getDrawable(
                            root.context,
                            building.resIds[i]
                        )
                    )
                }
            }
        }
    }

    private fun onBackButtonClicked() {
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun onPhotosButtonClicked() {
        if (otherPhotoLayoutIsVisible) {
            with(binding) {
                photosLayout.fadeOut()
                photosButton.animate()
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(shortAnimationDuration.toLong())
                    .withEndAction {
                        photosButton.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .duration = shortAnimationDuration.toLong()
                        photosButton.setImageDrawable(
                            AppCompatResources.getDrawable(
                                root.context,
                                R.drawable.ic_other_photos
                            )
                        )
                    }
            }
            otherPhotoLayoutIsVisible = false
        } else {
            with(binding) {
                photosLayout.fadeIn()
                photosButton.animate()
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(shortAnimationDuration.toLong())
                    .withEndAction {
                        photosButton.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .duration = shortAnimationDuration.toLong()
                        photosButton.setImageDrawable(
                            AppCompatResources.getDrawable(
                                root.context,
                                R.drawable.ic_close
                            )
                        )
                    }
            }
            otherPhotoLayoutIsVisible = true
        }
    }

    private fun View.fadeOut() {
        with(this) {
            animate()
                .alpha(0f)
                .translationY(60f)
                .setDuration(mediumAnimationDuration.toLong())
                .withEndAction {
                    this.visibility = View.GONE
                }
        }
    }

    private fun View.fadeIn() {
        with(this) {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .translationY(-60f)
                .setDuration(mediumAnimationDuration.toLong())
        }
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
        const val MAX_NUMBER_OF_PHOTOS_TO_DISPLAY = 3
    }
}