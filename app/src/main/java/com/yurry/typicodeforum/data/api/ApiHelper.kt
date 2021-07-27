package com.yurry.typicodeforum.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getAlbumsByUserId(userId: Int) = apiService.getAlbumsByUserId(userId)
    fun getComments(postId: Int) = apiService.getComments(postId)
    fun getPhotosByAlbumId(albumId: Int) = apiService.getPhotosByAlbumId(albumId)
    fun getPosts() = apiService.getPosts()
    fun getUsers() = apiService.getUsers()
    fun getUserById(userId: Int) = apiService.getUserById(userId)
}