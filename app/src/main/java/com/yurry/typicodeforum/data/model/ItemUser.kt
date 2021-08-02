package com.yurry.typicodeforum.data.model

import androidx.annotation.Keep

@Keep
data class ItemUser(
    val user: User,
    val albums: List<Album>
)
