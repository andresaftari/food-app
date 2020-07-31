package com.andresaftari.foodapi.views

import com.andresaftari.foodapi.data.local.Meal

// This interface is "View" section which contains local data from "Model"
interface CategoryView {
    fun showLoading()
    fun hideLoading()
    // setMeal used to inject List of Meal data
    fun setMeal(meals: List<Meal>)
    fun onErrorLoading(message: String?)
}