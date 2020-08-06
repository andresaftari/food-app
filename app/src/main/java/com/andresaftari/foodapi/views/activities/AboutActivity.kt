package com.andresaftari.foodapi.views.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andresaftari.foodapi.R
import kotlinx.android.synthetic.main.activity_about.*

@SuppressLint("SetTextI18n")
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Set the textview with my contact
        tv_nameAbout.text = "PRASIDYA PRAMADRESANA SAFTARI"
        tv_linkedIn.text = "https://www.linkedin.com/in/prasidya-pramadresana-saftari/"
        tv_emailAbout.text = "andresaftari@gmail.com"
        tv_contactAbout.text = "instagram.com/andresaftari"

        // Redirect the user to my LinkedIn page
        tv_linkedIn.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).setData(
                    Uri.parse("https://www.linkedin.com/in/prasidya-pramadresana-saftari/")
                )
            )
        }

        // Redirect the user to my Instagram profile
        tv_contactAbout.setOnClickListener {
            startActivity(
                Intent(Intent.ACTION_VIEW).setData(
                    Uri.parse("https://www.instagram.com/andresaftari/")
                )
            )
        }
    }
}