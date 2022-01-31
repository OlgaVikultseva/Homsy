package com.example.homsy.presentation.choicebuilding.adapter

data class BuildingItem(
    val id: Int,
    val location: String,
    val buildingType: String,
    val description: String,
    val price: String,
    val resId: Int,
//    val imageUrl: String,
    val isFavorite: Boolean
)
