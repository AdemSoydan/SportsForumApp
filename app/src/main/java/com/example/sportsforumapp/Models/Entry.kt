package com.example.sportsforumapp.Models

import java.io.Serializable

data class Entry (val eId: Int? ,val entryText : String?, val title : Title?, val user: User?,val noOfLiked : Int?): Serializable