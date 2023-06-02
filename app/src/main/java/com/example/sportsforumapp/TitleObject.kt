package com.example.sportsforumapp

import com.example.sportsforumapp.Models.Title


object TitleObject {
    private lateinit var title : Title

    fun setTitle(title1 : Title){
        title = title1
    }

    fun getTitle() : Title {
        return title
    }
}