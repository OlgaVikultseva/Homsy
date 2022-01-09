package com.example.homsy.presentation.choicebuilding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homsy.data.DataSource
import com.example.homsy.presentation.choicebuilding.adapter.BuildingItem
import com.example.homsy.presentation.choicebuilding.adapter.CategoryItem

class ChoiceBuildingViewModel : ViewModel() {

    private val dataSource = DataSource()

    private val _categories = MutableLiveData<List<CategoryItem>>()
    val categories: LiveData<List<CategoryItem>> = _categories

    private val _buildings = MutableLiveData<List<BuildingItem>>()
    val buildings: LiveData<List<BuildingItem>> = _buildings

    init {
        _categories.value = dataSource.getCategoryList()
        _buildings.value = dataSource.getBuildingList()
    }

    fun onCategoryClicked(categoryId: Int) {
        // todo установить для категории с таким id isSelected = true, для остальных false
    }

    fun onBuildingClicked(buildingId: Int) {
        // todo перейти на другой экран
    }

    fun onFavoriteIconClicked(buildingId: Int) {
        // todo установить для здания с таким id isFavorite = true
    }
}