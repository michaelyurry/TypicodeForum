package com.yurry.typicodeforum.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.model.MainPost
import kotlinx.android.synthetic.main.item_post_layout.view.*

class PostAdapter(private val posts: ArrayList<MainPost>
) : RecyclerView.Adapter<PostAdapter.DataViewHolder>() {
    private var mListener: RecyclerViewClickListener? = null

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: MainPost, mListener: RecyclerViewClickListener?) {
            itemView.tv_title.text = post.title
            itemView.tv_body.text = post.body

            itemView.tv_username.text = post.name
            itemView.tv_company.text = post.company
            itemView.setOnClickListener{mListener?.onClick(post)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_post_layout, parent,
                false
            )

        )

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(posts[position], mListener)

    fun addPosts(list: List<MainPost>, listener: RecyclerViewClickListener) {
        posts.addAll(list)
        mListener = listener
    }

    interface RecyclerViewClickListener{
        fun onClick(model: MainPost)
    }

}
