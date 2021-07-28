package com.yurry.typicodeforum.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurry.typicodeforum.data.model.Photo
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PhotoViewModel(private val mainRepository: MainRepository, private val albumId: Int): ViewModel() {
    private val photos = MutableLiveData<Resource<List<Photo>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() {
        photos.postValue(Resource.loading())
        compositeDisposable.add(
            mainRepository.getPhotosFromAlbum(albumId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    photos.postValue(Resource.success(it))
                }, {
                    photos.postValue(Resource.error(it.message.toString()))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getPhotos(): LiveData<Resource<List<Photo>>> {
        return photos
    }

}