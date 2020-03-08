package com.dd.cleanarchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dd.cleanarchitecture.R
import com.dd.core.data.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoriesListAdapter(var categories: ArrayList<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.CategoryViewHolder>() {

    fun updateCategories(newCategories: List<Category>) {
        categories.clear()
        categories.addAll(newCategories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryName = view.tvName

        fun bind(category: Category) {
            categoryName.text = category.name

        }
    }


}