package com.yurry.typicodeforum.data.model

import com.google.gson.annotations.SerializedName

data class Photo (
    @SerializedName("id")
    val id : Int = 0,
    @SerializedName("albumId")
    val albumId : Int = 0,
    @SerializedName("title")
    val title : String = "",
    @SerializedName("url")
    val url : String = "",
    @SerializedName("thumbnailUrl")
    val thumbnailUrl : String = ""
)