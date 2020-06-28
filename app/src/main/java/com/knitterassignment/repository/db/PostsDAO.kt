package com.knitterassignment.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostsDAO {

    @Throws
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewPost(post: Post)

    @Query("SELECT * FROM postsTable")
    suspend fun getPosts(): List<Post>

    @Query("DELETE FROM postsTable")
    suspend fun delete()
}