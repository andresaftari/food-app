package com.andresaftari.foodapi.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Meal
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_selected_category.view.*

class SelectedCategoryAdapter(private val list: List<Meal>) :
    RecyclerView.Adapter<SelectedCategoryAdapter.SelectedCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectedCategoryViewHolder =
        SelectedCategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_selected_category, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: SelectedCategoryViewHolder, position: Int) {
        holder.bind(list[position])

        val meals = list[position]
        holder.itemView.setOnClickListener {
            Snackbar.make(
                holder.itemView,
                "Hitted ${meals.strMeal}!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    inner class SelectedCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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