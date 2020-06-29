package com.knitterassignment.repository.db.comments

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDAO {

    @Throws
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewComment(comment: Comment)

    @Query("SELECT * FROM commentsTable")
    suspend fun getComments(): List<Comment>

    @Query("DELETE FROM commentsTable")
    suspend fun delete()
}