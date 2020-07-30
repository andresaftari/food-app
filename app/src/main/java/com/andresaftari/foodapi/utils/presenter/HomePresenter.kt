package com.andresaftari.foodapi.utils.presenter

import android.util.Log
import com.andresaftari.foodapi.data.model.Categories
import com.andresaftari.foodapi.data.model.Meals
import com.andresaftari.foodapi.utils.api.ApiUtils
import com.andresaftari.foodapi.views.HomeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* This "Presenter" used to process data from API and Models, it connects the communication
* between "Model" (with API) and "View"
*/
class HomePresenter(private val view: HomeView) {
    fun getMeals() {
        // Start the loading indicator when getMeals is executed
        view.showLoading()

        // Initiation of "Meals" model calling
        val callMeals: Call<Meals> = ApiUtils().getAPI().getMeal()

        // Callback the "Meals" model
        callMeals.enqueue(object : Callback<Meals> {
            override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                // If Callback is responsed, hide the loading indicator
                view.hideLoading()

                // Check whether the response is successful or not and it is null or not
                if (response.isSuccessful && response.body() != null)
                // If the response is successful and not null, set the response body with meals
                    view.setMeals(response.body()!!.meals)
                else
                // If the response is failed, set the response body with error message
                    view.onErrorLoading(response.message())
            }

            override fun onFailure(call: Call<Meals>, t: Throwable) {
                // If no response at all, hide the loading indicator and show the error message
                view.hideLoading()
                view.onErrorLoading(t.localizedMessage)

                // Send the fail Logs to Logcat (i) section for error details
                Log.i(
                    "HomePresenter.meals",
                    "Failed! ${t.message} --- ${t.printStackTrace()}"
                )
            }
        })
    }

    fun getCategories() {
        // Start the loading indicator when getCategories is executed
        view.showLoading()

        // Initiation of "Categories" model calling
        val callCategories: Call<Categories> = ApiUtils().getAPI().getCategories()

        // Callback the "Categories" model
        callCategories.enqueue(object : Callback<Categories> {
            override fun onResponse(call: Call<Categories>, response: Response<Categories>) {
                // If Callback is responsed, hide the loading indicator
                view.hideLoading()

                // Check whether the response is successful or not and it is null or not
                if (response.isSuccessful && response.body() != null)
                // If the response is successful and not null, set the response body with categories
                    view.setCategories(response.body()!!.categories)
                else
                // If the response is failed, set the response body with error message
                    view.onErrorLoading(response.message())
            }

            override fun onFailure(call: Call<Categories>, t: Throwable) {
                // If no response at all, hide the loading indicator and show the error message
                view.hideLoading()
                view.onErrorLoading(t.localizedMessage)

                // Send the fail Logs to Logcat (i) section for error details
                Log.i(
                    "HomePresenter.categories",
                    "Failed! ${t.message} --- ${t.printStackTrace()}"
                )
            }
        })
    }
}