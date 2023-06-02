package com.example.sportsforumapp.SportsApi

import com.example.sportsforumapp.Models.Title
import com.example.sportsforumapp.Models.TitleResponse
import com.example.sportsforumapp.Models.TitleSaveRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TitleService {
    @GET("/title")
    fun getTitles() : Call<List<TitleResponse>>

    @POST("/title")
    fun saveTitle(@Body title : Title) : Call<Title>

    @POST("/titlesave")
    fun saveTitleWithRequest(@Body title : TitleSaveRequest) : Call<Title>

    @GET("/titles/{id}")
    fun getTitleById(@Path("id") id : Int) : Call<Title>
}