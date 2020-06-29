package com.knitterassignment.repository.db.posts

import androidx.paging.DataSource

class DBPostsDataSourceFactory(var postDAO: PostDAO): DataSource.Factory<String, Post>() {

    private var dbPostsPageKeyedDataSource: DBPostsPageKeyedDataSource =
        DBPostsPageKeyedDataSource(postDAO)

    override fun create(): DataSource<String, Post> {
        return dbPostsPageKeyedDataSource
    }
}