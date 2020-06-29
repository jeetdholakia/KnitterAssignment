package com.knitterassignment.repository.network.paging

import androidx.paging.DataSource
import com.knitterassignment.repository.db.posts.Post
import rx.subjects.ReplaySubject


class PostsDataSourceFactory: DataSource.Factory<String, Post>() {

    private val TAG = "PostsDataSourceFactory"
    private var postsPageKeyedDataSource: PostsPageKeyedDataSource =
        PostsPageKeyedDataSource()

    override fun create(): DataSource<String, Post> {
        return postsPageKeyedDataSource
    }

    fun getPosts(): ReplaySubject<Post> {
        return postsPageKeyedDataSource.getPosts()
    }
}