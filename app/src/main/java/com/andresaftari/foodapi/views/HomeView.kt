package com.andresaftari.foodapi.views

import com.andresaftari.foodapi.data.local.Category
import com.andresaftari.foodapi.data.local.Meal

// This interface is "View" section which contains local data from "Model"
interface HomeView {
    fun showLoading()
    fun hideLoading()

    // setMeals used to inject List of Meal data
    fun setMeals(meals: List<Meal>)
    // setCategories used to inject List of Category data
    fun setCategories(categories: List<Category>)

    fun onErrorLoading(message: String?)
}
