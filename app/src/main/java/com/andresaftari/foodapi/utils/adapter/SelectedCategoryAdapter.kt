package com.andresaftari.foodapi.utils.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Meal
import com.andresaftari.foodapi.views.activities.DetailActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_selected_category.view.*

class SelectedCategoryAdapter(private val list: List<Meal>) :
    RecyclerView.Adapter<SelectedCategoryAdapter.SelectedCategoryViewHolder>() {

    // onCreateViewHolder used to inflate layout to be used in SelectedCategoryViewHolder as View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedCategoryViewHolder =
        SelectedCategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_selected_category, parent, false)
        )

    // getItemCount used to gather size of the items needed (our List of Meal size)
    override fun getItemCount(): Int = list.size

    // onBindViewHolder used to bind data from Model with View from ViewHolder for each item
    override fun onBindViewHolder(holder: SelectedCategoryViewHolder, position: Int) {
        // Bind data with ViewHolder for each item positioned in the list
        holder.bind(list[position])

        val meal = list[position]
        // Move to DetailActivity after clicking selected meal
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_DETAIL, meal.strMeal)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    inner class SelectedCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Binding data from Meal with View
        fun bind(meal: Meal) {
            with(itemView) {
                Glide.with(context)
                    .load(meal.strMealThumb)
                    .into(iv_selectedCatThumb)

                tv_selectedCatName.text = meal.strMeal
            }
        }
    }
}