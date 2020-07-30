package com.andresaftari.foodapi.data.model

import com.andresaftari.foodapi.data.local.Meal
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Meals(
    @SerializedName("meals")
    @Expose
    val meals: List<Meal>
)