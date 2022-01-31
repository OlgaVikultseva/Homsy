package com.example.homsy.presentation.buildingdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homsy.data.DataSource
import com.example.homsy.presentation.choicebuilding.adapter.BuildingItem

class BuildingDetailsViewModel(buildingId: Int) : ViewModel() {

    private val dataSource = DataSource()

    private val _building = MutableLiveData<BuildingItem?>()
    val building: LiveData<BuildingItem?> = _building

    init {
        _building.value = dataSource.getBuilding(buildingId)
    }

    fun onFavoriteIconClicked(buildingId: Int) {
        // todo установить для здания с таким id isFavorite = true
    }
}

class BuildingDetailsViewModelFactory(private val buildingId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BuildingDetailsViewModel::class.java)) {
            return BuildingDetailsViewModel(buildingId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}