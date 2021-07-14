package com.fmahromi.lks_software_aplication_jabar_android_2021.api

import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseMenu
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponsePost
import com.fmahromi.lks_software_aplication_jabar_android_2021.model.ResponseUsers
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("login.php")
    @FormUrlEncoded
    fun postLogin(
        @Field("username") username : String,
        @Field("password") password :String
    ):Call<ResponseUsers>

    @POST("register.php")
    @FormUrlEncoded
    fun postRegister(
        @Field("username") username : String,
        @Field("password") password :String
    ):Call<ResponseUsers>

    @GET("getMenu.php")
    fun getMenu():Call<ResponseMenu>

    @POST("postMenu.php")
    @FormUrlEncoded
    fun postMenu(
        @Field("name") name : String,
        @Field("description") description : String,
        @Field("price") price : Int
    ): Call<ResponsePost<Any>>

    @PUT("updateMenu.php")
    @FormUrlEncoded
    fun putMenu(
        @Field("id") id : Int,
        @Field("name") name : String,
        @Field("description") description : String,
        @Field("price") price : Int
    ): Call<ResponseMenu>



    @DELETE("deleteMenu.php")
    @FormUrlEncoded
    fun deleteMenu(
        @Field("name") id : Int,
    ): Call<Void?>?

}