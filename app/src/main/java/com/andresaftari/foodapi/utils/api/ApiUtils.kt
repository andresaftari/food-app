package com.andresaftari.foodapi.utils.api

import android.content.Context
import androidx.appcompat.app.AlertDialog

// This ApiUtils class used to activate the initiated retrofit from ApiClient
class ApiUtils {
    // getAPI will connect and redirect the Base URL with the path provided in ApiService
    fun getAPI(): ApiService = ApiClient().retrofit().create(ApiService::class.java)

    fun showDialog(context: Context, title: String, message: String): AlertDialog {
        val dialog = AlertDialog.Builder(context).setTitle(title).setMessage(message).show()

        if (dialog.isShowing) dialog.cancel()
        return dialog
    }
}