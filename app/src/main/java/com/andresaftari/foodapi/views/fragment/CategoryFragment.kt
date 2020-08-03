package com.andresaftari.foodapi.views.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Meal
import com.andresaftari.foodapi.utils.adapter.CategoryPagerAdapter
import com.andresaftari.foodapi.utils.adapter.SelectedCategoryAdapter
import com.andresaftari.foodapi.utils.api.ApiUtils
import com.andresaftari.foodapi.utils.presenter.CategoryPresenter
import com.andresaftari.foodapi.views.CategoryView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment(), CategoryView {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_category, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check whether the arguments is null or not
        if (arguments != null) {
            tv_detailCat.apply {
                // if the it is not null, get data from the argument with String EXTRA_DATA_DESC
                text = arguments!!
                    .getString(CategoryPagerAdapter.EXTRA_DATA_DESC)
                    ?.replace("\r\n\r\n", "\n\n")

                // Give the scrolling ability to the tv_detailCat if the description is overflowed
                movementMethod = ScrollingMovementMethod()
            }

            // Set the category cover with the thumbnail
            Glide.with(activity!!)
                .load(arguments?.getString(CategoryPagerAdapter.EXTRA_DATA_IMG))
                .into(iv_bgCat)

            // Initiate the presenter
            val categoryPresenter = CategoryPresenter(this)
            // Set the presenter for this fragment to load data within String EXTRA_DATA_CATEGORY
            categoryPresenter.getMealSelectedCategory(
                arguments!!.getString(CategoryPagerAdapter.EXTRA_DATA_CATEGORY).toString()
            )
        }
    }

    // Show shimmer and progress bar when loading data
    override fun showLoading() {
        pb_loading_cat?.visibility = View.VISIBLE
        shimmer_selectedCat?.visibility = View.VISIBLE
    }

    // Hide shimmer and progress bar when data loaded
    override fun hideLoading() {
        pb_loading_cat?.visibility = View.GONE
        shimmer_selectedCat?.visibility = View.GONE
    }

    // Inject meals to RecyclerView rv_selectedCat
    override fun setMeal(meals: List<Meal>) {
        val selectedCategoryAdapter = SelectedCategoryAdapter(meals)

        // Apply adapter to rv_header
        rv_selectedCat.apply {
            adapter = selectedCategoryAdapter
            // GridLayoutManager setup
            layoutManager = GridLayoutManager(activity!!, 2)
        }
        selectedCategoryAdapter.notifyDataSetChanged()
    }

    // Initialization of dialog when error occurred
    override fun onErrorLoading(message: String?) {
        ApiUtils().showDialog(activity!!, "Failed to fetch data!", message)
    }
}