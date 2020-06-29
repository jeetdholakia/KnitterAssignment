package com.knitterassignment.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.knitterassignment.R
import com.knitterassignment.repository.db.posts.Post
import kotlinx.android.synthetic.main.item_view.view.*

class PostListAdapter(private val onClickListener: (Int) -> Unit) : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private var posts = listOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return PostViewHolder(view)
    }

    public fun setPosts(transactions: List<Post>) {
        this.posts = transactions
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindData(posts[position])

        holder.itemView.setOnClickListener {
            onClickListener.invoke(position)
        }
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(data : Post) {
            itemView.tvTitle.text = data.title
            itemView.tvDate.text = "Click to open comments"
            itemView.tvDesc.text = data.body
        }
    }
}