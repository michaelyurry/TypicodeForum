package com.yurry.typicodeforum.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.ui.viewmodel.CommentViewModel
import com.yurry.typicodeforum.ui.viewmodel.PostViewModel

@Suppress("UNCHECKED_CAST")
class CommentViewModelFactory(private val apiHelper: ApiHelper, private val postId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            return CommentViewModel(MainRepository(apiHelper), postId) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}