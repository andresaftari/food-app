package com.andresaftari.foodapi.views.fragment

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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
    private lateinit var dialog: AlertDialog.Builder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_category, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            tv_detailCat.apply {
                text = arguments!!
                    .getString(CategoryPagerAdapter.EXTRA_DATA_DESC)
                    ?.replace("\r\n\r\n", "\n\n")

                movementMethod = ScrollingMovementMethod()
            }

            Glide.with(activity!!)
                .load(arguments?.getString(CategoryPagerAdapter.EXTRA_DATA_IMG))
                .into(iv_bgCat)

            dialog = AlertDialog.Builder(activity!!).apply {
                setTitle(arguments!!.getString(CategoryPagerAdapter.EXTRA_DATA_CATEGORY))
                setMessage(
                    arguments!!
                        .getString(CategoryPagerAdapter.EXTRA_DATA_DESC)
                        ?.replace("\r\n\r\n", "\n\n")
                )
            }

            val categoryPresenter = CategoryPresenter(this)
            categoryPresenter.getMealSelectedCategory(
                arguments!!.getString(CategoryPagerAdapter.EXTRA_DATA_CATEGORY).toString()
            )

            cv_cat.setOnClickListener { onClick() }
        }
    }

    override fun showLoading() {
        pb_loading_cat?.visibility = View.VISIBLE
        shimmer_selectedCat.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_loading_cat?.visibility = View.GONE
        shimmer_selectedCat.visibility = View.GONE
    }

    override fun setMeal(meals: List<Meal>) {
        val selectedCategoryAdapter = SelectedCategoryAdapter(meals)

        rv_selectedCat.apply {
            adapter = selectedCategoryAdapter
            layoutManager = GridLayoutManager(activity!!, 2)
        }
    }

    override fun onErrorLoading(message: String?) {
        ApiUtils().showDialog(activity!!, "Failed to fetch data!", message)
    }

    fun onClick() {
        dialog.setPositiveButton("CLOSE", { dialog, _ -> dialog.dismiss() })
        dialog.show()
    }
}