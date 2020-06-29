package com.knitterassignment.repository.network.posts

import com.knitterassignment.repository.network.model.posts.PostsResponse
import com.knitterassignment.util.Constants.Companion.postsEndpoint
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET(postsEndpoint)
    fun getPagedPosts(@Query("page") pageNumber: Int): Call<PostsResponse>

    @GET(postsEndpoint)
    suspend fun getPosts(@Query("page") pageNumber: Int): PostsResponse
}