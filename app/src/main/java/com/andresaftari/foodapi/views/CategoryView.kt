package com.andresaftari.foodapi.views

import com.andresaftari.foodapi.data.local.Meal

interface CategoryView {
    fun showLoading()
    fun hideLoading()
    fun setMeal(meals: List<Meal>)
    fun onErrorLoading(message: String?)
}