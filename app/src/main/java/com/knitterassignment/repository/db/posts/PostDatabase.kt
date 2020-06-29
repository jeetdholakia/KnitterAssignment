package com.knitterassignment.repository.db.posts

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.knitterassignment.util.Constants.Companion.postsTableName
import java.util.concurrent.Executor
import java.util.concurrent.Executors


@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostsDatabase: RoomDatabase() {
    abstract fun postsDAO(): PostDAO
    private lateinit var postsPaged: LiveData<PagedList<Post>>

    companion object {
        @Volatile private var instance: PostsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: buildDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PostsDatabase::class.java,
            postsTableName
        ).build()
    }

    private fun init() {
        val pagedListConfig =
            PagedList.Config.Builder().setEnablePlaceholders(false)
                .setInitialLoadSizeHint(Int.MAX_VALUE)
                .setPageSize(Int.MAX_VALUE).build()
        val executor: Executor = Executors.newFixedThreadPool(3)
        val dataSourceFactory = DBPostsDataSourceFactory(postsDAO())
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        postsPaged = livePagedListBuilder.setFetchExecutor(executor).build()
    }

    open fun getPosts(): LiveData<PagedList<Post>> {
        return postsPaged
    }
}