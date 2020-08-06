package com.andresaftari.foodapi.views.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.data.local.Category
import com.andresaftari.foodapi.utils.adapter.CategoryPagerAdapter
import kotlinx.android.synthetic.main.activity_category.*

@Suppress("Unchecked_cast")
class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        initActionBar()
        initIntent()
    }

    private fun initIntent() {
        // When this function is called, get the Serializable data from List of category with EXTRA
        val categories = intent?.getSerializableExtra(MainActivity.EXTRA_CATEGORY) as List<Category>
        val position = intent.getIntExtra(MainActivity.EXTRA_POSITION, 0)

        // Set the CategoryPagerAdapter as an Adapter for View Pager with List of Category
        val adapter = CategoryPagerAdapter(supportFragmentManager, categories)
        main_pager?.adapter = adapter

        // Set ViewPager into TabLayout and set the SmoothScroll feature as TRUE
        tabs.setupWithViewPager(main_pager)
        main_pager.setCurrentItem(position, true)

        adapter.notifyDataSetChanged()
    }

    private fun initActionBar() {
        // When this function is called, set the actionBar with custom toolbar and set "Back" menu
        setSupportActionBar(mToolbar)
        if (supportActionBar != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // Start the "Back" button to Home activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}