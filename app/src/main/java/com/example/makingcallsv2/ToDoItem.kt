package com.example.makingcallsv2

import com.squareup.moshi.Json


data class ToDoItem(

    @Json(name = "userId")
    val userId: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "completed")
    val completed: Boolean,

    @Json(name = "id")
    val id: Int? = null
)
