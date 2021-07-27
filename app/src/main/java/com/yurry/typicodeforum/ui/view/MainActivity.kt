package com.yurry.typicodeforum.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.api.ApiServiceImpl
import com.yurry.typicodeforum.data.model.ItemPost
import com.yurry.typicodeforum.ui.adapter.PostAdapter
import com.yurry.typicodeforum.ui.base.PostViewModelFactory
import com.yurry.typicodeforum.ui.viewmodel.PostViewModel
import com.yurry.typicodeforum.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), PostAdapter.RecyclerViewClickListener {
    private val postViewModel: PostViewModel by lazy {
        ViewModelProviders.of(
            this,
            PostViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(PostViewModel::class.java)
    }
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        postViewModel.getDatas().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    it.data?.let { posts -> renderPosts(posts) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderPosts(itemPostList: List<ItemPost>) {
        adapter.addPosts(itemPostList, this)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(model: ItemPost) {
        val intent = Intent(this, DetailPostActivity::class.java)
        intent.putExtra("id", model.id)
        intent.putExtra("name", model.name)
        intent.putExtra("userId", model.userId)
        intent.putExtra("title", model.title)
        intent.putExtra("body", model.body)
        startActivity(intent)
    }
}