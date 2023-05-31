package com.example.sportsforumapp.Models

import java.io.Serializable

data class Title(val tId: Int?, val titleName: String?, val entries:List<Entry>?):Serializable

