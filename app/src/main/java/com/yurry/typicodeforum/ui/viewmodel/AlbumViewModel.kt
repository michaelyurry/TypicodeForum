package com.yurry.typicodeforum.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurry.typicodeforum.data.model.Album
import com.yurry.typicodeforum.data.model.User
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class AlbumViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val albums = MutableLiveData<Resource<List<Album>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchAlbums()
    }

    private fun fetchAlbums() {
        albums.postValue(Resource.loading())
        compositeDisposable.add(
            mainRepository.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    albums.postValue(Resource.success(it))
                }, {
                    albums.postValue(Resource.error("Something Went Wrong"))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getAlbums(): LiveData<Resource<List<Album>>> {
        return albums
    }

}