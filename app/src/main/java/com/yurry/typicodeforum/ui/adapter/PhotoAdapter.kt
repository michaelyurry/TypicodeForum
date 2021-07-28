package com.yurry.typicodeforum.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.model.Photo
import kotlinx.android.synthetic.main.item_photo_layout.view.*

class PhotoAdapter(private val photos: ArrayList<Photo>
) : RecyclerView.Adapter<PhotoAdapter.DataViewHolder>()  {
    private var mListener: RecyclerViewClickListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(photo: Photo, mListener: RecyclerViewClickListener?) {
            Picasso.get().load(photo.thumbnailUrl).into(itemView.iv_photo_image)
            itemView.iv_photo_title.text = photo.title
            itemView.setOnClickListener{mListener?.onClick(photo)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo_layout, parent,
                false
            )

        )

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(photos[position], mListener)

    fun addphotos(list: List<Photo>, listener: RecyclerViewClickListener) {
        photos.addAll(list)
        mListener = listener
    }

    interface RecyclerViewClickListener{
        fun onClick(model: Photo)
    }
}