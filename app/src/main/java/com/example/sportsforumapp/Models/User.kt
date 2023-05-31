package com.example.sportsforumapp.Models

import java.io.Serializable

data class User(val userId : Int ,val userName: String?, val email : String?, val password: String?, val entries : List<Entry>?
): Serializable