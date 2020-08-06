package com.andresaftari.foodapi.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.andresaftari.foodapi.R
import com.andresaftari.foodapi.views.activities.MainActivity
import java.lang.Exception

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val background = object : Thread() {
            override fun run() {
                try {
                    sleep(3500)
                    startActivity(Intent(baseContext, MainActivity::class.java))
                } catch (e: Exception) {
                    Log.i(
                        "SplashScreen",
                        "Failed! ${e.message} --- ${e.printStackTrace()}"
                    )
                }
            }
        }
        background.start()
    }
}