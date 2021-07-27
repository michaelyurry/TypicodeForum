package com.yurry.typicodeforum.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.ui.viewmodel.UserViewModel

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val apiHelper: ApiHelper, private val userId: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(MainRepository(apiHelper), userId) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}