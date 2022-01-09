package com.example.homsy.presentation.choicebuilding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.homsy.databinding.ItemBuildingBinding

class BuildingAdapter(
    private val buildingClickListener: (Int) -> Unit
) : RecyclerView.Adapter<BuildingAdapter.BuildingViewHolder>() {

    var buildings = listOf<BuildingItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder {
        val binding = ItemBuildingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BuildingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {
        holder.bind(buildings[position])
        holder.itemView.setOnClickListener {
            buildingClickListener(buildings[position].id)
        }
    }

    override fun getItemCount(): Int =
        buildings.size

    class BuildingViewHolder(private val binding: ItemBuildingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(building: BuildingItem) {
            binding.apply {
                locationTextView.text = building.location
                buildingTypeTextView.text = building.buildingType
                buildingImageView.setImageDrawable(
                    AppCompatResources.getDrawable(
                        binding.root.context,
                        building.resId
                    )
                )
            }
        }
    }
}