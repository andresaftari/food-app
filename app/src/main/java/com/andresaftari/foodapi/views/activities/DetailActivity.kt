package com.andresaftari.foodapi.views.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Meal
import com.andresaftari.foodapi.utils.api.ApiUtils
import com.andresaftari.foodapi.utils.presenter.DetailPresenter
import com.andresaftari.foodapi.views.DetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    // Presenter initiation
    private lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Call the custom actionBar function
        setupActionBar()

        if (intent.hasExtra(EXTRA_DETAIL)) {
            // Set the presenter for this View if intent has EXTRA_DETAIL data
            detailPresenter = DetailPresenter(this)

            // Get intent data with EXTRA_DETAIL data and set the presenter with it
            val detailExtra = intent.getStringExtra(EXTRA_DETAIL)!!
            detailPresenter.getMealByName(detailExtra)
        }
    }

    // Show progressBar when loading data
    override fun showLoading() {
        pb_loading_detail?.visibility = View.VISIBLE
    }

    // Hide progressBar when loading data
    override fun hideLoading() {
        pb_loading_detail?.visibility = View.GONE
    }

    // This function used to inject meal details this activity
    override fun setMeal(meals: Meal) {
        // Set up the meal name as Collapsing Toolbar title
        collapsing_toolbar.title = meals.strMeal

        // Set the meal thumbnail for this activity
        Glide.with(this)
            .load(meals.strMealThumb)
            .into(iv_mealThumbDetail)

        // Set the meal category name and origin
        tv_dotCatDetail.text = meals.strCategory
        tv_dotCountryDetail.text = meals.strArea

        // Set the meal instructions
        tv_dotInstructionDetail.apply {
            text = meals.strInstructions
                .replace("\r\n\r\n", "\n\n")
                .replace("\r\n", "\n\n")
        }
        setupActionBar()

        // Call the ingredient setup function
        setUpIngredient(meals)

        // Set the button to open the Youtube video of this recipe when it's clicked
        btn_youtube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            startActivity(intent.setData(Uri.parse(meals.strYoutube)))
        }

        // Set the button to open the Source page of this recipe when it's clicked
        btn_source.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            startActivity(intent.setData(Uri.parse(meals.strSource)))
        }
    }

    // Start the "Back" button to Home activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    // Initialization of dialog when error occurred
    override fun onErrorLoading(message: String?) {
        ApiUtils().showDialog(this, "Failed to fetch data!", message!!)
    }

    // If ingredients are not empty, show them and the measurement in this activity
    private fun setUpIngredient(meals: Meal) {
        if (meals.strIngredient1.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient1}")
        if (meals.strIngredient2.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient2}")
        if (meals.strIngredient3.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient3}")
        if (meals.strIngredient4.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient4}")
        if (meals.strIngredient5.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient5}")
        if (meals.strIngredient6.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient6}")
        if (meals.strIngredient7.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient7}")
        if (meals.strIngredient8.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient8}")
        if (meals.strIngredient9.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient9}")
        if (meals.strIngredient10.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient10}")
        if (meals.strIngredient11.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient11}")
        if (meals.strIngredient12.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient12}")
        if (meals.strIngredient13.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient13}")
        if (meals.strIngredient14.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient14}")
        if (meals.strIngredient15.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient15}")
        if (meals.strIngredient16.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient16}")
        if (meals.strIngredient17.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient17}")
        if (meals.strIngredient18.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient18}")
        if (meals.strIngredient19.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient19}")
        if (meals.strIngredient20.isNotEmpty()) ingredient.append("\n \u2022 ${meals.strIngredient20}")

        if (meals.strIngredient1.isNotEmpty() && !Character.isWhitespace(meals.strMeasure1[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure1}")
        if (meals.strIngredient2.isNotEmpty() && !Character.isWhitespace(meals.strMeasure2[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure2}")
        if (meals.strIngredient3.isNotEmpty() && !Character.isWhitespace(meals.strMeasure3[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure3}")
        if (meals.strIngredient4.isNotEmpty() && !Character.isWhitespace(meals.strMeasure4[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure4}")
        if (meals.strIngredient5.isNotEmpty() && !Character.isWhitespace(meals.strMeasure5[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure5}")
        if (meals.strIngredient6.isNotEmpty() && !Character.isWhitespace(meals.strMeasure6[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure6}")
        if (meals.strIngredient7.isNotEmpty() && !Character.isWhitespace(meals.strMeasure7[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure7}")
        if (meals.strIngredient8.isNotEmpty() && !Character.isWhitespace(meals.strMeasure8[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure8}")
        if (meals.strIngredient9.isNotEmpty() && !Character.isWhitespace(meals.strMeasure9[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure9}")
        if (meals.strIngredient10.isNotEmpty() && !Character.isWhitespace(meals.strMeasure10[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure10}")
        if (meals.strIngredient11.isNotEmpty() && !Character.isWhitespace(meals.strMeasure11[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure11}")
        if (meals.strIngredient12.isNotEmpty() && !Character.isWhitespace(meals.strMeasure12[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure12}")
        if (meals.strIngredient13.isNotEmpty() && !Character.isWhitespace(meals.strMeasure13[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure13}")
        if (meals.strIngredient14.isNotEmpty() && !Character.isWhitespace(meals.strMeasure14[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure14}")
        if (meals.strIngredient15.isNotEmpty() && !Character.isWhitespace(meals.strMeasure15[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure15}")
        if (meals.strIngredient16.isNotEmpty() && !Character.isWhitespace(meals.strMeasure16[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure16}")
        if (meals.strIngredient17.isNotEmpty() && !Character.isWhitespace(meals.strMeasure17[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure17}")
        if (meals.strIngredient18.isNotEmpty() && !Character.isWhitespace(meals.strMeasure18[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure18}")
        if (meals.strIngredient19.isNotEmpty() && !Character.isWhitespace(meals.strMeasure19[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure19}")
        if (meals.strIngredient20.isNotEmpty() && !Character.isWhitespace(meals.strMeasure20[0]))
            tv_measureDetail.append("\n \u2022 ${meals.strMeasure20}")
    }

    // Set the custom toolbar as an actionBar for this activity
    private fun setupActionBar() {
        setSupportActionBar(toolbar)

        // set the CollapsingToolbar when collapse change colors
        collapsing_toolbar.apply {
            setContentScrimColor(
                ContextCompat.getColor(
                    this@DetailActivity,
                    R.color.colorWhite
                )
            )
            setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    this@DetailActivity,
                    R.color.colorPrimary
                )
            )
            setExpandedTitleColor(
                ContextCompat.getColor(
                    this@DetailActivity,
                    R.color.colorWhite
                )
            )
        }

        // Set the actionBar with custom toolbar and set "Back" menu
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}