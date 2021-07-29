package com.yurry.typicodeforum.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.api.ApiServiceImpl
import com.yurry.typicodeforum.data.model.Album
import com.yurry.typicodeforum.data.model.ItemUser
import com.yurry.typicodeforum.ui.adapter.AlbumAdapter
import com.yurry.typicodeforum.ui.base.UserViewModelFactory
import com.yurry.typicodeforum.ui.viewmodel.UserViewModel
import com.yurry.typicodeforum.utils.Status
import kotlinx.android.synthetic.main.activity_user_detail.*

class DetailUserActivity: AppCompatActivity(), AlbumAdapter.RecyclerViewClickListener {
    private val userViewModel: UserViewModel by lazy {
        ViewModelProvider(
            this,
            UserViewModelFactory(
                ApiHelper(ApiServiceImpl()),
                intent.getIntExtra("userId", 0))
        ).get(UserViewModel::class.java)
    }
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setupUI()
        setupObserver()
    }

    private fun setupUI(){
        supportActionBar?.title = javaClass.simpleName
        albumRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AlbumAdapter(arrayListOf())
        albumRecyclerView.addItemDecoration(
            DividerItemDecoration(
                albumRecyclerView.context,
                (albumRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        albumRecyclerView.adapter = adapter
    }

    private fun setupObserver() {
        userViewModel.getUser().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    cl_item_user.visibility = View.VISIBLE
                    it.data?.let { itemUser -> renderUI(itemUser) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    cl_item_user.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderUI(itemUser: ItemUser){
        tv_name.text = itemUser.user.name
        tv_email.text = itemUser.user.name
        tv_address.text = compileAddress(itemUser)
        tv_company.text = itemUser.user.company.name

        adapter.addAlbums(itemUser.albums, this)
    }

    private fun compileAddress(itemUser: ItemUser): String{
        return itemUser.user.address.street+"\n"+itemUser.user.address.suite+"\n"+itemUser.user.address.city+"\n"+itemUser.user.address.zipcode
    }

    override fun onClick(model: Album) {
        val intent = Intent(this, DetailAlbumActivity::class.java)
        intent.putExtra("albumId", model.id)
        intent.putExtra("albumTitle", model.title)
        startActivity(intent)
    }
}