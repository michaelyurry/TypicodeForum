package com.yurry.typicodeforum.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yurry.typicodeforum.data.model.MainPost
import com.yurry.typicodeforum.data.model.Post
import com.yurry.typicodeforum.data.model.User
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.utils.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PostViewModel(private val mainRepository: MainRepository): ViewModel() {
    private val datas = MutableLiveData<Resource<List<MainPost>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchDataPost()
    }

    private fun fetchDataPost() {
        datas.postValue(Resource.loading())
        compositeDisposable.add(
            Observable.zip(mainRepository.getUsers(), mainRepository.getPosts(),
                { u, p -> combineData(u, p)})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    datas.postValue(Resource.success(it))
                },{
                    datas.postValue(Resource.error("Something Went Wrong"))
                })
        )
    }

    private fun combineData(userList: List<User>, postList: List<Post>): List<MainPost>{
        val dataList = arrayListOf<MainPost>()
        for (post in postList){
            val user = userList.find { it.id == post.userId }!!
            val data = MainPost(post.id, post.title, post.body, post.userId, user.name, user.company.name)
            dataList.add(data)
        }
        return dataList
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getDatas(): LiveData<Resource<List<MainPost>>> {
        return datas
    }

}