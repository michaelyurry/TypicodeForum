package com.yurry.typicodeforum.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurry.typicodeforum.data.model.Album
import com.yurry.typicodeforum.data.model.ItemUser
import com.yurry.typicodeforum.data.model.User
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.utils.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserViewModel(private val mainRepository: MainRepository,  private val userId: Int): ViewModel() {
    private val itemUsers = MutableLiveData<Resource<ItemUser>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchData()
    }

    private fun fetchData(){
        itemUsers.postValue(Resource.loading())
        compositeDisposable.add(
            Observable.zip(mainRepository.getUserById(userId),
                mainRepository.getAlbumsByUserId(userId),
                {u, a -> combineData(u, a)})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    itemUsers.postValue(Resource.success(it))
                },{
                    itemUsers.postValue(Resource.error(it.message.toString()))
                })
        )
    }

    private fun combineData(user: User, albumList: List<Album>): ItemUser{
        return ItemUser(user, albumList)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUser(): LiveData<Resource<ItemUser>> {
        return itemUsers
    }

}