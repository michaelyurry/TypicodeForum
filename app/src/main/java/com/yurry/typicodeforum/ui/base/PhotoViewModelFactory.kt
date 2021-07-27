package com.yurry.typicodeforum.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.ui.viewmodel.PhotoViewModel

class PhotoViewModelFactory (private val apiHelper: ApiHelper, private val albumId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoViewModel::class.java)) {
            return PhotoViewModel(MainRepository(apiHelper), albumId) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}