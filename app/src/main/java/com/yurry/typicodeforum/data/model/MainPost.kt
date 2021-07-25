package com.yurry.typicodeforum.data.model

data class MainPost(
    val id : Int = 0,
    val title : String = "",
    val body : String = "",
    val userId : Int = 0,
    val name : String = "",
    val company: String = ""
)
