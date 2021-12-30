package com.example.homsy.presentation.choicebuilding.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.homsy.databinding.ItemCategoryBinding

class CategoryAdapter(
    private val categoryClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var categories = listOf<CategoryItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
        holder.itemView.setOnClickListener {
            categoryClickListener(categories[position].id)
        }
    }

    override fun getItemCount(): Int =
        categories.size

    class CategoryViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: CategoryItem) {
            binding.categoryNameTextView.text = category.name
            if (category.isSelected) {
                binding.categoryNameTextView.isSelected = true
                binding.dot.visibility = View.VISIBLE
            } else {
                binding.categoryNameTextView.isSelected = false
                binding.dot.visibility = View.INVISIBLE
            }
        }
    }
}