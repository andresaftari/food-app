package com.andresaftari.foodapi.utils.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// This ApiClient used to initiate the API base URL, HTTP logging interceptor and Retrofit builder
class ApiClient {
    companion object {
        const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    }

    // Initiation of retrofit
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOKHTTP())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Initiation of OKHTTPClient
    private fun provideOKHTTP(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(provideLoggingInterceptor())
        .build()

    // Initiation of HTTPLoggingInterceptor
    private fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor().level = HttpLoggingInterceptor.Level.BODY
        }
}