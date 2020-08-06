package com.andresaftari.foodapi.views

import com.andresaftari.foodapi.data.local.Meal

// This interface is "View" section which contains local data from "Model"
interface DetailView {
    fun showLoading()
    fun hideLoading()

    // setMeal used to inject one meal data
    fun setMeal(meals: Meal)
    fun onErrorLoading(message: String?)
}