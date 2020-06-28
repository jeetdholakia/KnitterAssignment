package com.knitterassignment.repository

import com.knitterassignment.repository.db.Post

interface PostsRepository {

    suspend fun getPosts(): List<Post>?
}