package com.yurry.typicodeforum.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurry.typicodeforum.data.model.Comment
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CommentViewModel(private val mainRepository: MainRepository, private val postId: Int): ViewModel() {
    private val comments = MutableLiveData<Resource<List<Comment>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchComments()
    }

    private fun fetchComments() {
        comments.postValue(Resource.loading())
        compositeDisposable.add(
            mainRepository.getComments(postId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    comments.postValue(Resource.success(it))
                }, {
                    comments.postValue(Resource.error("Something Went Wrong"))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getComments(): LiveData<Resource<List<Comment>>> {
        return comments
    }

}