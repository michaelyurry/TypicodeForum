package com.yurry.typicodeforum.data.api

import com.yurry.typicodeforum.data.model.*
import io.reactivex.Observable

class ApiServiceImpl: ApiService {
    private val apiService = ApiService.create()
    override fun getAlbums(): Observable<List<Album>> {
        return apiService.getAlbums()
    }

    override fun getComments(postId: Int): Observable<List<Comment>> {
        return apiService.getComments(postId)
    }

    override fun getPhotos(): Observable<List<Photo>> {
        return apiService.getPhotos()
    }

    override fun getPosts(): Observable<List<Post>> {
        return apiService.getPosts()
    }

    override fun getUsers(): Observable<List<User>> {
        return apiService.getUsers()
    }
}