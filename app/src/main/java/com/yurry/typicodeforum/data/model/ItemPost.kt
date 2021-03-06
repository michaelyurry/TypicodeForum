package com.yurry.typicodeforum.data.model

import androidx.annotation.Keep

@Keep
data class ItemPost(
    val id : Int = 0,
    val title : String = "",
    val body : String = "",
    val userId : Int = 0,
    val name : String = "",
    val company: String = ""
)
