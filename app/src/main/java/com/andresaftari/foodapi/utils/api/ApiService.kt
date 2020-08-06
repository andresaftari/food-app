package com.andresaftari.foodapi.utils.api

import com.andresaftari.foodapi.data.model.Categories
import com.andresaftari.foodapi.data.model.Meals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// This interface will gather every available data from the directed API path
interface ApiService {
    // getMeal will gather every available Italian meals in the API
    @GET("filter.php?a=Italian")
    fun getMeal(): Call<Meals>

    // getCategories will gather every available Meal Categories in the API
    @GET("categories.php")
    fun getCategories(): Call<Categories>

    // getMealByCategory will gather every available Meal based on the chosen category
    @GET("filter.php")
    fun getMealByCategory(@Query("c") categoryName: String): Call<Meals>

    // getMealByName will gather every available Meal based on the searched name
    @GET("search.php")
    fun getMealByName(@Query("s") mealName: String): Call<Meals>
}