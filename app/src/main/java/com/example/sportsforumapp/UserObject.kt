package com.example.sportsforumapp

import com.example.sportsforumapp.Models.User

object UserObject {
    private lateinit var user : User

    fun setUser(user1 : User){
        user = user1
    }

    fun getUser() : User {
        return user
    }
}