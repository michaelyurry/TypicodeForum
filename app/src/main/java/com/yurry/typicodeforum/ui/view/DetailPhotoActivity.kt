package com.yurry.typicodeforum.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.yurry.typicodeforum.R
import kotlinx.android.synthetic.main.activity_photo_detail.*

class DetailPhotoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_detail)
        Picasso.get().load(intent.getStringExtra("photoUrl")).into(iv_image)
    }
}