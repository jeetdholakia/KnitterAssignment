package com.knitterassignment.repository.network.paging

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.knitterassignment.repository.db.posts.Post
import com.knitterassignment.repository.network.paging.PostsDataSourceFactory
import com.knitterassignment.util.Constants.Companion.loadingPageSize
import com.orhanobut.logger.Logger
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PostsNetwork(private var dataSourceFactory: PostsDataSourceFactory, private var boundaryCallback: PagedList.BoundaryCallback<Post>) {

    private var postsPaged: LiveData<PagedList<Post>>

    init {
        Logger.d("PostsNetwork: init")
        val pagedListConfig =
            PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(loadingPageSize).setPageSize(loadingPageSize).build()

        val executor: Executor =
            Executors.newFixedThreadPool(3)

        val livePagedListBuilder= LivePagedListBuilder(dataSourceFactory, pagedListConfig)

        postsPaged = livePagedListBuilder.setFetchExecutor(executor).setBoundaryCallback(boundaryCallback)
                .build()
    }

    fun getPagedPosts(): LiveData<PagedList<Post>> {
        return postsPaged
    }
}