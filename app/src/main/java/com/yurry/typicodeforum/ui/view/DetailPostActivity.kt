package com.yurry.typicodeforum.ui.view

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
import com.yurry.typicodeforum.data.model.Comment
import com.yurry.typicodeforum.ui.adapter.CommentAdapter
import com.yurry.typicodeforum.ui.base.CommentViewModelFactory
import com.yurry.typicodeforum.ui.viewmodel.CommentViewModel
import com.yurry.typicodeforum.utils.Status
import kotlinx.android.synthetic.main.activity_post_detail.*

class DetailPostActivity: AppCompatActivity() {
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var adapter: CommentAdapter
    private var authorId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        authorId = intent.getIntExtra("userId", 0)
        setupUI()
        setupViewModel(intent.getIntExtra("id", 0))
        setupObserver()
    }

    private fun setupUI() {
        tv_post_title.text = intent.getStringExtra("title")
        tv_post_body.text = intent.getStringExtra("body")
        tv_post_author.text = intent.getStringExtra("name")

        commentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CommentAdapter(arrayListOf())
        commentRecyclerView.addItemDecoration(
            DividerItemDecoration(
                commentRecyclerView.context,
                (commentRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        commentRecyclerView.adapter = adapter
    }

    private fun setupObserver() {
        commentViewModel.getComments().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    commentRecyclerView.visibility = View.VISIBLE
                    it.data?.let { comments -> renderComments(comments) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    commentRecyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderComments(commentList: List<Comment>) {
        adapter.addComments(commentList)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel(postId: Int) {
        commentViewModel = ViewModelProviders.of(
            this,
            CommentViewModelFactory(ApiHelper(ApiServiceImpl()), postId)
        ).get(CommentViewModel::class.java)
    }
}