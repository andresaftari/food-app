package com.andresaftari.foodapi.utils.presenter

import android.util.Log
import com.andresaftari.foodapi.data.model.Meals
import com.andresaftari.foodapi.utils.api.ApiUtils
import com.andresaftari.foodapi.views.DetailView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/* This "Presenter" used to process data from API and Models, it connects the communication
* between "Model" (with API) and "View"
*/
class DetailPresenter(private val view: DetailView) {
    fun getMealByName(mealName: String) {
        // Start the loading indicator when getMealByName is executed
        view.showLoading()

        // Initiation of "Meals" model calling
        val callMeals = ApiUtils().getAPI().getMealByName(mealName)

        // Callback the "Meals" model
        callMeals.enqueue(object : Callback<Meals> {
            override fun onResponse(call: Call<Meals>, response: Response<Meals>) {
                // If Callback is responsed, hide the loading indicator
                view.hideLoading()

                // Check whether the response is successful or not and it is null or not
                if (response.isSuccessful && response.body() != null)
                // If the response is successful and not null, set the response body with first meal
                    view.setMeal(response.body()!!.meals[0])
                else
                // If the response is failed, set the response body with error message
                    view.hideLoading()
            }

            override fun onFailure(call: Call<Meals>, t: Throwable) {
                // If no response at all, hide the loading indicator and show the error message
                view.hideLoading()
                view.onErrorLoading(t.localizedMessage)

                // Send the fail Logs to Logcat (i) section for error details
                Log.i(
                    "DetailPresenter.meals",
                    "Failed! ${t.message} --- ${t.printStackTrace()}"
                )
            }
        })
    }
}