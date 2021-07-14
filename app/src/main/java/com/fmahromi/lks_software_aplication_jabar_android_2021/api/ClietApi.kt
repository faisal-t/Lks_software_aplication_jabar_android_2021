package com.fmahromi.lks_software_aplication_jabar_android_2021.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ClietApi{
    val BASE_URL = "http://192.168.0.113/lks-contoh/api/"

    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val api =  retrofit.create(ApiInterface::class.java)
}