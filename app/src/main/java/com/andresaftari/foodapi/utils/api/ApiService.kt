package com.andresaftari.foodapi.utils.api

import com.andresaftari.foodapi.data.model.Categories
import com.andresaftari.foodapi.data.model.Meals
import retrofit2.Call
import retrofit2.http.GET

// This interface will gather every available data from the directed API path
interface ApiService {
    // getMeal will gather every available Italian meals in the API
    @GET("filter.php?a=Italian")
    fun getMeal(): Call<Meals>

    // getCategories will gather every available Meal Categories in the API
    @GET("categories.php")
    fun getCategories(): Call<Categories>
}