package com.yurry.typicodeforum.data.repository

import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.model.*
import io.reactivex.Observable

class MainRepository(private val apiHelper: ApiHelper) {
    fun getAlbumsByUserId(userId: Int): Observable<List<Album>> {
        return apiHelper.getAlbumsByUserId(userId)
    }
    fun getComments(postId: Int): Observable<List<Comment>> {
        return apiHelper.getComments(postId)
    }
    fun getPhotosFromAlbum(albumId: Int): Observable<List<Photo>> {
        return apiHelper.getPhotosByAlbumId(albumId)
    }
    fun getPosts(): Observable<List<Post>> {
        return apiHelper.getPosts()
    }
    fun getUsers(): Observable<List<User>> {
        return apiHelper.getUsers()
    }
    fun getUserById(userId: Int): Observable<User> {
        return apiHelper.getUserById(userId)
    }
}