package com.knitterassignment.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.knitterassignment.util.Constants.Companion.tableName

@Database(entities = arrayOf(Post::class), version = 1, exportSchema = false)
abstract class PostsDatabase: RoomDatabase() {
    abstract fun postsDAO(): PostsDAO

    companion object {
        @Volatile private var instance: PostsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PostsDatabase::class.java,
            tableName
        ).build()
    }
}