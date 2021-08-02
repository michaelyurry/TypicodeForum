package com.yurry.typicodeforum.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Album(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("userId")
    val userId : Int = 0,
    @SerializedName("title")
    val title : String = ""
)
