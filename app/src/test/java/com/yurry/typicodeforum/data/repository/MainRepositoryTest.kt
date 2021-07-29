package com.yurry.typicodeforum.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.model.*
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyInt
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MainRepositoryTest {
    @Rule
    @JvmField
    val rule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiHelper: ApiHelper
    private lateinit var repository: MainRepository

    @Before
    fun setUp(){
        repository = MainRepository(apiHelper)
    }

    @Test
    fun testGetAlbumsByUserId_success() {
        val albumList = listOf(
            Album(1, 2, "Title1"),
            Album(2, 3, "Title2"),
            Album(3, 4, "Title3")
        )
        `when`(apiHelper.getAlbumsByUserId(anyInt())).thenReturn(Observable.just(albumList))
        val result = repository.getAlbumsByUserId(anyInt())
        val testObserver = TestObserver<List<Album>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].id, `is`(1))
        assertThat(listResult[0].userId, `is`(2))
        assertThat(listResult[0].title, `is`("Title1"))
        assertThat(listResult[1].id, `is`(2))
        assertThat(listResult[1].userId, `is`(3))
        assertThat(listResult[1].title, `is`("Title2"))
        assertThat(listResult[2].id, `is`(3))
        assertThat(listResult[2].userId, `is`(4))
        assertThat(listResult[2].title, `is`("Title3"))
    }

    @Test
    fun testGetAlbumsByUserId_error() {
        `when`(apiHelper.getAlbumsByUserId(anyInt())).thenReturn(Observable.just(listOf()))
        val testObserver = TestObserver<List<Album>>()
        repository.getAlbumsByUserId(anyInt()).subscribe(testObserver)
        testObserver.onError(Exception("Data Empty"))
        testObserver.assertComplete()
        assertEquals(testObserver.errors()[0].message, "Data Empty")
    }

    @Test
    fun testGetComments_success() {
        val commentList = listOf(
            Comment(1, 2, "Name1", "Email1", "Body1"),
            Comment(2, 3, "Name2", "Email2", "Body2"),
            Comment(3, 4, "Name3", "Email3", "Body3")
        )
        `when`(apiHelper.getComments(anyInt())).thenReturn(Observable.just(commentList))
        val result = repository.getComments(anyInt())
        val testObserver = TestObserver<List<Comment>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].id, `is`(1))
        assertThat(listResult[0].postId, `is`(2))
        assertThat(listResult[0].name, `is`("Name1"))
        assertThat(listResult[0].email, `is`("Email1"))
        assertThat(listResult[0].body, `is`("Body1"))
        assertThat(listResult[1].id, `is`(2))
        assertThat(listResult[1].postId, `is`(3))
        assertThat(listResult[1].name, `is`("Name2"))
        assertThat(listResult[1].email, `is`("Email2"))
        assertThat(listResult[1].body, `is`("Body2"))
        assertThat(listResult[2].id, `is`(3))
        assertThat(listResult[2].postId, `is`(4))
        assertThat(listResult[2].name, `is`("Name3"))
        assertThat(listResult[2].email, `is`("Email3"))
        assertThat(listResult[2].body, `is`("Body3"))
    }

    @Test
    fun testGetComments_error() {
        `when`(apiHelper.getComments(anyInt())).thenReturn(Observable.just(listOf()))
        val testObserver = TestObserver<List<Comment>>()
        repository.getComments(anyInt()).subscribe(testObserver)
        testObserver.onError(Exception("Data Empty"))
        testObserver.assertComplete()
        assertEquals(testObserver.errors()[0].message, "Data Empty")
    }

    @Test
    fun testGetPhotosFromAlbum_success() {
        val photoList = listOf(
            Photo(1, 2, "Title1", "Url1", "ThumbnailUrl1"),
            Photo(2, 3, "Title2", "Url2", "ThumbnailUrl2"),
            Photo(3, 4, "Title3", "Url3", "ThumbnailUrl3")
        )
        `when`(apiHelper.getPhotosByAlbumId(anyInt())).thenReturn(Observable.just(photoList))
        val result = repository.getPhotosFromAlbum(anyInt())
        val testObserver = TestObserver<List<Photo>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(3))
        assertThat(listResult[0].id, `is`(1))
        assertThat(listResult[0].albumId, `is`(2))
        assertThat(listResult[0].title, `is`("Title1"))
        assertThat(listResult[0].url, `is`("Url1"))
        assertThat(listResult[0].thumbnailUrl, `is`("ThumbnailUrl1"))
        assertThat(listResult[1].id, `is`(2))
        assertThat(listResult[1].albumId, `is`(3))
        assertThat(listResult[1].title, `is`("Title2"))
        assertThat(listResult[1].url, `is`("Url2"))
        assertThat(listResult[1].thumbnailUrl, `is`("ThumbnailUrl2"))
        assertThat(listResult[2].id, `is`(3))
        assertThat(listResult[2].albumId, `is`(4))
        assertThat(listResult[2].title, `is`("Title3"))
        assertThat(listResult[2].url, `is`("Url3"))
        assertThat(listResult[2].thumbnailUrl, `is`("ThumbnailUrl3"))
    }

    @Test
    fun testGetPhotosFromAlbum() {
        `when`(apiHelper.getPhotosByAlbumId(anyInt())).thenReturn(Observable.just(listOf()))
        val testObserver = TestObserver<List<Photo>>()
        repository.getPhotosFromAlbum(anyInt()).subscribe(testObserver)
        testObserver.onError(Exception("Data Empty"))
        testObserver.assertComplete()
        assertEquals(testObserver.errors()[0].message, "Data Empty")
    }

    @Test
    fun testGetPosts_success() {
        val postList = listOf(
            Post(1, 2, "Title1", "Body1"),
            Post(2, 3, "Title2", "Body2")
        )
        `when`(apiHelper.getPosts()).thenReturn(Observable.just(postList))
        val result = repository.getPosts()
        val testObserver = TestObserver<List<Post>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(2))
        assertThat(listResult[0].id, `is`(1))
        assertThat(listResult[0].userId, `is`(2))
        assertThat(listResult[0].title, `is`("Title1"))
        assertThat(listResult[0].body, `is`("Body1"))
        assertThat(listResult[1].id, `is`(2))
        assertThat(listResult[1].userId, `is`(3))
        assertThat(listResult[1].title, `is`("Title2"))
        assertThat(listResult[1].body, `is`("Body2"))
    }

    @Test
    fun testGetPosts() {
        `when`(apiHelper.getPosts()).thenReturn(Observable.just(listOf()))
        val testObserver = TestObserver<List<Post>>()
        repository.getPosts().subscribe(testObserver)
        testObserver.onError(Exception("Data Empty"))
        testObserver.assertComplete()
        assertEquals(testObserver.errors()[0].message, "Data Empty")
    }

    @Test
    fun testGetUsers_success() {
        val userList = listOf(
            User(1, "Name1", "Username1", "Email1", Address(geo = Geo()), "Phone1", "Website1", Company()),
            User(2, "Name2", "Username2", "Email2", Address(geo = Geo()), "Phone2", "Website2", Company()),
        )
        `when`(apiHelper.getUsers()).thenReturn(Observable.just(userList))
        val result = repository.getUsers()
        val testObserver = TestObserver<List<User>>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val listResult = testObserver.values()[0]
        assertThat(listResult.size, `is`(2))
        assertThat(listResult[0].id, `is`(1))
        assertThat(listResult[0].name, `is`("Name1"))
        assertThat(listResult[0].username, `is`("Username1"))
        assertThat(listResult[0].email, `is`("Email1"))
        assertThat(listResult[0].phone, `is`("Phone1"))
        assertThat(listResult[0].website, `is`("Website1"))
        assertThat(listResult[1].id, `is`(2))
        assertThat(listResult[1].name, `is`("Name2"))
        assertThat(listResult[1].username, `is`("Username2"))
        assertThat(listResult[1].email, `is`("Email2"))
        assertThat(listResult[1].phone, `is`("Phone2"))
        assertThat(listResult[1].website, `is`("Website2"))
    }

    @Test
    fun testGetUsers() {
        `when`(apiHelper.getUsers()).thenReturn(Observable.just(listOf()))
        val testObserver = TestObserver<List<User>>()
        repository.getUsers().subscribe(testObserver)
        testObserver.onError(Exception("Data Empty"))
        testObserver.assertComplete()
        assertEquals(testObserver.errors()[0].message, "Data Empty")
    }

    @Test
    fun testGetUserById_success() {
        val user = User(1, "Name", "Username", "Email", Address(geo = Geo()), "Phone", "Website", Company())
        `when`(apiHelper.getUserById(anyInt())).thenReturn(Observable.just(user))
        val result = repository.getUserById(anyInt())
        val testObserver = TestObserver<User>()
        result.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()

        val value = testObserver.values()[0]
        assertThat(value.id, `is`(1))
        assertThat(value.name, `is`("Name"))
        assertThat(value.username, `is`("Username"))
        assertThat(value.email, `is`("Email"))
        assertThat(value.phone, `is`("Phone"))
        assertThat(value.website, `is`("Website"))
    }

    @Test
    fun testGetUserById() {
        `when`(apiHelper.getUserById(anyInt())).thenReturn(Observable.error(Exception("Data Null")))
        val testObserver = TestObserver<User>()
        repository.getUserById(anyInt()).subscribe(testObserver)
        testObserver.assertNotComplete()
        assertEquals(testObserver.errors()[0].message, "Data Null")

    }
}