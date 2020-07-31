package com.andresaftari.foodapi.views.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Category
import com.andresaftari.foodapi.data.local.Meal
import com.andresaftari.foodapi.utils.adapter.CategoryAdapter
import com.andresaftari.foodapi.utils.adapter.MealAdapter
import com.andresaftari.foodapi.utils.api.ApiUtils
import com.andresaftari.foodapi.utils.presenter.HomePresenter
import com.andresaftari.foodapi.views.HomeView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    HomeView {

    companion object {
        const val EXTRA_CATEGORY = "category"
        const val EXTRA_POSITION = "position"
    }

    // Presenter initiation
    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Presenter implementation
        presenter = HomePresenter(this)

        presenter.getMeals()
        presenter.getCategories()
    }

    // Show shimmers when loading data
    override fun showLoading() {
        header_shimmer.visibility = View.VISIBLE
        category_shimmer.visibility = View.VISIBLE
    }

    // Hide shimmers when data loaded
    override fun hideLoading() {
        header_shimmer.visibility = View.GONE
        category_shimmer.visibility = View.GONE
    }

    // Inject meals to RecyclerView rv_header
    override fun setMeals(meals: List<Meal>) {
        val mealAdapter = MealAdapter(meals)

        // Apply adapter to rv_header
        rv_header.apply {
            adapter = mealAdapter
            // LinearLayoutmanager setup
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        mealAdapter.notifyDataSetChanged()
    }

    // Inject categories to RecyclerView rv_category
    override fun setCategories(categories: List<Category>) {
        val categoryAdapter = CategoryAdapter(categories)

        // Apply adapter to rv_category
        rv_category.apply {
            adapter = categoryAdapter
            // GridLayoutManager setup
            layoutManager = GridLayoutManager(
                this@MainActivity,
                2,
                GridLayoutManager.VERTICAL,
                false
            )
            // Enabling nestedScrolling feature for rv_category
            isNestedScrollingEnabled = true
        }
        categoryAdapter.notifyDataSetChanged()
    }

    // Initialization of dialog when error occurred
    override fun onErrorLoading(message: String?) {
        ApiUtils().showDialog(this, "Failed to fetch data!", message!!)
    }
}