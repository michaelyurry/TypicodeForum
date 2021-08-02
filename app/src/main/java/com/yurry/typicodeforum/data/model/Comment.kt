package com.yurry.typicodeforum.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Comment(
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("postId")
    val postId : Int = 0,
    @SerializedName("name")
    val name : String = "",
    @SerializedName("email")
    val email : String = "",
    @SerializedName("body")
    val body : String = ""
)
