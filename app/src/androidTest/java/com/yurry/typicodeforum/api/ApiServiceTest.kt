package com.yurry.typicodeforum.api

import androidx.lifecycle.Observer
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.model.User
import com.yurry.typicodeforum.data.repository.MainRepository
import com.yurry.typicodeforum.ui.viewmodel.UserViewModel
import com.yurry.typicodeforum.utils.Resource
import junit.framework.TestCase
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.mockito.*
import java.net.HttpURLConnection

class ApiServiceTest: TestCase() {
    private lateinit var apiHelper: ApiHelper

    private lateinit var viewModel: UserViewModel
    @Mock
    private lateinit var apiUserObserver: Observer<Resource<List<User>, Any?>>
    private lateinit var mockWebServer: MockWebServer

    @Before
    override fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = UserViewModel(Mockito.mock(MainRepository::class.java))
        viewModel.getUsers().observeForever(apiUserObserver)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiHelper = Mockito.mock(ApiHelper::class.java)
    }
}