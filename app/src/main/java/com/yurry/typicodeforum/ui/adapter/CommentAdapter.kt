package com.yurry.typicodeforum.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yurry.typicodeforum.R
import com.yurry.typicodeforum.data.model.Comment
import kotlinx.android.synthetic.main.item_comment_layout.view.*

class CommentAdapter(private val comments: ArrayList<Comment>
) : RecyclerView.Adapter<CommentAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: Comment) {
            itemView.tv_comment_author.text = comment.name
            itemView.tv_comment_body.text = comment.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_comment_layout, parent,
                false
            )

        )

    override fun getItemCount(): Int = comments.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(comments[position])

    fun addComments(list: List<Comment>) {
        comments.addAll(list)
    }

}