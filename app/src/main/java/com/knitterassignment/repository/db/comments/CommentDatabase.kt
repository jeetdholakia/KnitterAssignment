package com.knitterassignment.repository.db.comments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.knitterassignment.util.Constants.Companion.commentsTableName

@Database(entities = [Comment::class], version = 1, exportSchema = false)
abstract class CommentDatabase: RoomDatabase() {
    abstract fun commentDAO(): CommentDAO

    companion object {
        @Volatile private var instance: CommentDatabase? = null
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
            CommentDatabase::class.java,
            commentsTableName
        ).build()
    }
}