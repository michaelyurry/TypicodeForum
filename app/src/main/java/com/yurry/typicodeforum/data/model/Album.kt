package com.yurry.typicodeforum.data.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("userId")
    val userId : Int = 0,
    @SerializedName("title")
    val title : String = ""
)
