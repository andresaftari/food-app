package com.andresaftari.foodapi.utils.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.andresaftari.foodapi.data.local.Category
import com.andresaftari.foodapi.views.fragment.CategoryFragment

class CategoryPagerAdapter(fm: FragmentManager, private val categories: List<Category>) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    // Initiation of Strings used as constant for arguments or intent
    companion object {
        const val EXTRA_DATA_DESC = "extra_desc"
        const val EXTRA_DATA_CATEGORY = "extra_cat"
        const val EXTRA_DATA_IMG = "extra_img"
    }

    override fun getItem(position: Int): Fragment {
        // Initiation of fragment that will be used
        val fragment = CategoryFragment()
        val bundle = Bundle()

        // Set the argument which will be sent using Bundle with String constants
        bundle.putString(EXTRA_DATA_DESC, categories[position].strCategoryDescription)
        bundle.putString(EXTRA_DATA_CATEGORY, categories[position].strCategory)
        bundle.putString(EXTRA_DATA_IMG, categories[position].strCategoryThumb)

        // Initiate the argument to be used in fragment
        fragment.arguments = bundle
        return fragment
    }

    // Get the size of the category List
    override fun getCount(): Int = categories.size

    // Get the title of category for each position
    override fun getPageTitle(position: Int): CharSequence? = categories[position].strCategory
}