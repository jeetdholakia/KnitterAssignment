package com.knitterassignment.repository

import com.knitterassignment.repository.db.posts.Post

interface PostsRepository {

    suspend fun getPosts(): List<Post>?
}