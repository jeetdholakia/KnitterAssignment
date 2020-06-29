package com.knitterassignment.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knitterassignment.R
import com.knitterassignment.repository.db.comments.Comment
import kotlinx.android.synthetic.main.item_view.view.*

class CommentsListAdapter(private val onClickListener: (Int) -> Unit) : RecyclerView.Adapter<CommentsListAdapter.CommentsViewHolder>() {

    private var posts = listOf<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comments_item_view, parent, false)
        return CommentsViewHolder(view)
    }

    public fun setComments(transactions: List<Comment>) {
        this.posts = transactions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bindData(posts[position])

        holder.itemView.setOnClickListener {
            onClickListener.invoke(position)
        }
    }


    class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data : Comment) {
            itemView.tvTitle.text = data.name
            itemView.tvDesc.text = data.body
        }
    }
}