package com.yurry.typicodeforum.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.ui.viewmodel.PostViewModel

@Suppress("UNCHECKED_CAST")
class PostViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}