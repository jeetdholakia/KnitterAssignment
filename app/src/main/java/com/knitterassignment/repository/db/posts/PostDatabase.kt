package com.knitterassignment.repository.db.posts

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.knitterassignment.util.Constants.Companion.postsTableName

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostsDatabase: RoomDatabase() {
    abstract fun postsDAO(): PostDAO

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
}