package com.andresaftari.foodapi.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Meal
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_pager.view.*

class MealAdapter(private val list: List<Meal>) :
    RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    // onCreateViewHolder used to inflate "item" layout to be used in MealViewHolder as View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_pager, parent, false)
        )

    // getItemCount used to gather size of the items needed (our List of Meal size)
    override fun getItemCount(): Int = list.size

    // onBindViewHolder used to bind data from Model with View from ViewHolder for each item
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        // Bind data with ViewHolder for each item positioned in the list
        holder.bind(list[position])

        val meals = list[position]
        // Create Snackbar notification for every single item when clicked
        holder.itemView.setOnClickListener {
            Snackbar.make(
                holder.itemView,
                "Hitted ${meals.strMeal}!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Binding data from Meal with View
        fun bind(meal: Meal) {
            with(itemView) {
                    Glide.with(context)
                        .load(meal.strMealThumb)
                        .into(iv_mealThumb)

                tv_mealName.text = meal.strMeal
            }
        }
    }
}