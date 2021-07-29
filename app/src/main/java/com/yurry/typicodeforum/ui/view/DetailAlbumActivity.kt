package com.yurry.typicodeforum.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.api.ApiHelper
import com.yurry.typicodeforum.data.api.ApiServiceImpl
import com.yurry.typicodeforum.data.model.Photo
import com.yurry.typicodeforum.ui.adapter.PhotoAdapter
import com.yurry.typicodeforum.ui.base.PhotoViewModelFactory
import com.yurry.typicodeforum.ui.viewmodel.PhotoViewModel
import com.yurry.typicodeforum.utils.Status
import kotlinx.android.synthetic.main.activity_album_detail.*


class DetailAlbumActivity: AppCompatActivity(), PhotoAdapter.RecyclerViewClickListener {
    private val photoViewModel: PhotoViewModel by lazy {
        ViewModelProvider(
            this,
            PhotoViewModelFactory(ApiHelper(ApiServiceImpl()), intent.getIntExtra("albumId", 0))
        ).get(PhotoViewModel::class.java)
    }
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        supportActionBar?.title = intent.getStringExtra("albumTitle")
        photoRecyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter = PhotoAdapter(arrayListOf())
        photoRecyclerView.adapter = adapter
    }

    private fun setupObserver() {
        photoViewModel.getPhotos().observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    photoRecyclerView.visibility = View.VISIBLE
                    it.data?.let { photos -> renderPhotos(photos) }
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    photoRecyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderPhotos(photos: List<Photo>) {
        adapter.addphotos(photos, this)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(model: Photo) {
        val intent = Intent(this, DetailPhotoActivity::class.java)
        intent.putExtra("photoUrl", model.url)
        intent.putExtra("photoTitle", model.title)
        startActivity(intent)
    }
}