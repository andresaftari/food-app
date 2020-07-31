package com.andresaftari.foodapi.data.model

import com.andresaftari.foodapi.data.local.Category
import java.io.Serializable

data class Categories(
    val categories: List<Category>
) : Serializable