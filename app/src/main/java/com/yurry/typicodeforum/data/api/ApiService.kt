package com.yurry.typicodeforum.data.api

import com.yurry.typicodeforum.data.model.*
import com.yurry.typicodeforum.utils.Constant
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("albums")
    fun getAlbums(): Observable<List<Album>>

    @GET("users/{userId}/albums")
    fun getAlbumsByUserId(@Path("userId") userId: Int): Observable<List<Album>>

    @GET("posts/{postId}/comments")
    fun getComments(@Path("postId") postId: Int): Observable<List<Comment>>

    @GET("albums/{albumId}/photos")
    fun getPhotosByAlbumId(@Path("albumId") albumId: Int): Observable<List<Photo>>

    @GET("posts")
    fun getPosts(): Observable<List<Post>>

    @GET("users")
    fun getUsers(): Observable<List<User>>

    @GET("users/{userId}")
    fun getUserById(@Path("userId") userId: Int): Observable<User>

    companion object Factory {
        fun create():ApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}