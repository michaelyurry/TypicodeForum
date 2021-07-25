package com.yurry.typicodeforum.data.repository

import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.model.*
import io.reactivex.Observable

class MainRepository(private val apiHelper: ApiHelper) {
    fun getAlbums(): Observable<List<Album>> {
        return apiHelper.getAlbums()
    }
    fun getComments(postId: Int): Observable<List<Comment>> {
        return apiHelper.getComments(postId)
    }
    fun getPhotos(): Observable<List<Photo>> {
        return apiHelper.getPhotos()
    }
    fun getPosts(): Observable<List<Post>> {
        return apiHelper.getPosts()
    }
    fun getUsers(): Observable<List<User>> {
        return apiHelper.getUsers()
    }
}