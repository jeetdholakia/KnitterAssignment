package com.knitterassignment.repository.db.posts

import android.util.Log
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBPostsPageKeyedDataSource(var postDAO: PostDAO): PageKeyedDataSource<String, Post>() {
    private val TAG = "DBPostsPageKeyedDataSource"

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Post>) {
        Log.i(TAG, "Loading Initial Rang, Count " + params.requestedLoadSize)

        CoroutineScope(Dispatchers.IO).launch {
            val posts = postDAO.getPosts()
            if(posts.isNotEmpty()) {
                callback.onResult(posts, "0", "1")
            }
        }

    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Post>) {

    }
}