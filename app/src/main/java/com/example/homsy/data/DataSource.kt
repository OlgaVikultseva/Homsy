package com.example.homsy.data

import com.example.homsy.R
import com.example.homsy.presentation.choicebuilding.adapter.BuildingItem
import com.example.homsy.presentation.choicebuilding.adapter.CategoryItem

class DataSource {

    fun getCategoryList(): List<CategoryItem> =
        listOf(
            CategoryItem(1, "Apartment", false),
            CategoryItem(2, "House", false),
            CategoryItem(3, "Near you!", true),
            CategoryItem(4, "Popular", false)
        )

    fun getBuildingList(): List<BuildingItem> =
        listOf(
            BuildingItem(1, "Victoria Village", "Apartment", "3000 $", R.drawable.apartment1, false),
            BuildingItem(2, "9 Rosamond Street", "House", "4600 $", R.drawable.house1, false),
            BuildingItem(3, "1505-85 Wood Street", "House", "5800 $", R.drawable.house3, false),
            BuildingItem(4, "6 Tamola Park Court", "Apartment", "2600 $", R.drawable.apartment2, false),
            BuildingItem(5, "34 Willowbrook Road", "Apartment", "3100 $", R.drawable.apartment3, false),
            BuildingItem(6, "Mono Amaranth Town Line", "House", "4000 $", R.drawable.house2, false),
        )
}