package com.example.sportsforumapp.SportsApi

import com.example.sportsforumapp.Models.Entry
import com.example.sportsforumapp.Models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SportsApiService {
    @POST("/user")
    fun postUser(@Body userData : User) : Call<User>

    @POST("/userlogin")
    fun getUser(@Body userData : User) : Call<User>

    @POST("/entry")
    fun saveEntry(@Body entry : Entry) : Call<Entry>

    @GET("/likeentry/{id}")
    fun likeEntry(@Path("id") id : Int) : Call<Int>

    @GET("/unlikeentry/{id}")
    fun unlikeEntry(@Path("id") id : Int) : Call<Int>
}