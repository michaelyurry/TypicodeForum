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
import com.yurry.typicodeforum.data.model.Comment
import com.yurry.typicodeforum.ui.adapter.CommentAdapter
import com.yurry.typicodeforum.ui.base.CommentViewModelFactory
import com.yurry.typicodeforum.ui.viewmodel.CommentViewModel
import com.yurry.typicodeforum.utils.Status
import kotlinx.android.synthetic.main.activity_post_detail.*

class DetailPostActivity: AppCompatActivity() {
    private val commentViewModel: CommentViewModel by lazy {
            ViewModelProvider(
                this,
                CommentViewModelFactory(
                ApiHelper(ApiServiceImpl()),
                intent.getIntExtra("id", 0))
        ).get(CommentViewModel::class.java)
    }
    private lateinit var adapter: CommentAdapter
    private var authorId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        authorId = intent.getIntExtra("userId", 0)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        supportActionBar?.title = javaClass.simpleName
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
        tv_post_author.setOnClickListener {
            val intent = Intent(this, DetailUserActivity::class.java)
            intent.putExtra("userId", authorId)
            startActivity(intent)
        }
    }

    private fun setupObserver() {
        commentViewModel.getComments().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    cl_post.visibility = View.VISIBLE
                    it.data?.let { comments -> renderComments(comments) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    cl_post.visibility = View.GONE
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
}