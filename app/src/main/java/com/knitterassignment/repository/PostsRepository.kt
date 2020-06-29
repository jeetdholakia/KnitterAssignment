package com.knitterassignment.repository

import com.knitterassignment.repository.db.posts.Post

interface PostsRepository {

    //fun getPagedPosts(): LiveData<PagedList<Post>>

    suspend fun getPosts(): List<Post>?
}