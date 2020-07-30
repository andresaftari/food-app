package com.andresaftari.foodapi.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Category
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val list: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // onCreateViewHolder used to inflate "item" layout to be used in CategoryViewHolder as View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )
    // getItemCount used to gather size of the items needed (our List of Category size)
    override fun getItemCount(): Int = list.size

    // onBindViewHolder used to bind data from Model with View from ViewHolder for each item
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        // Bind data with ViewHolder for each item positioned in the list
        holder.bind(list[position])

        val categories = list[position]
        // Create Snackbar notification for every single item when clicked
        holder.itemView.setOnClickListener {
            Snackbar.make(
                holder.itemView,
                "Hitted ${categories.strCategory}!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Binding data from Meal with View
        fun bind(category: Category) {
            with(itemView) {
                Glide.with(context)
                    .load(category.strCategoryThumb)
                    .into(iv_categoryThumb)

                tv_categoryName.text = category.strCategory
            }
        }
    }
}