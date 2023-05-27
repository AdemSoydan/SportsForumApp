package com.example.sportsforumapp.ApiUtils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ForumApiUtil {
    val BASE_URL = "http://10.0.2.2:8080/"


    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        //önce bu şekildeydi direkt string olarak almak istediğimiz için
        //.addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    fun getRetrofit(): Retrofit {
        return retrofit;
    }
}