package com.yurry.typicodeforum.data.api

class ApiHelper(private val apiService: ApiService) {
    fun getAlbums() = apiService.getAlbums()
    fun getComments(postId: Int) = apiService.getComments(postId)
    fun getPhotos() = apiService.getPhotos()
    fun getPosts() = apiService.getPosts()
    fun getUsers() = apiService.getUsers()
}