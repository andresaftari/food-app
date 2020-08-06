package com.andresaftari.foodapi.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
        val categories = intent?.getSerializableExtra(MainActivity.EXTRA_CATEGORY) as List<Category>
        val position = intent.getIntExtra(MainActivity.EXTRA_POSITION, 0)
        val adapter = CategoryPagerAdapter(supportFragmentManager, categories)

        main_pager?.adapter = adapter
        tabs.setupWithViewPager(main_pager)
        main_pager.setCurrentItem(position, true)

        adapter.notifyDataSetChanged()
    }

    private fun initActionBar() {
        setSupportActionBar(mToolbar)
        if (supportActionBar != null) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }
}