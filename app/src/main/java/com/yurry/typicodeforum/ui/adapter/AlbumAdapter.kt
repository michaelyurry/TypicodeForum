package com.yurry.typicodeforum.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.model.Album
import kotlinx.android.synthetic.main.item_album_layout.view.*

class AlbumAdapter(private val albums: ArrayList<Album>
) : RecyclerView.Adapter<AlbumAdapter.DataViewHolder>() {
    private var mListener: RecyclerViewClickListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album, mListener: RecyclerViewClickListener?) {
            itemView.tv_album_name.text = album.title
            itemView.setOnClickListener{mListener?.onClick(album)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_album_layout, parent,
                false
            )

        )

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(albums[position], mListener)

    fun addAlbums(list: List<Album>, listener: RecyclerViewClickListener) {
        albums.addAll(list)
        mListener = listener
    }

    interface RecyclerViewClickListener{
        fun onClick(model: Album)
    }
}