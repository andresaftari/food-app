package com.andresaftari.foodapi.data.local

import java.io.Serializable

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
) : Serializable