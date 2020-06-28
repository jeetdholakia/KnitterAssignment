package com.knitterassignment.repository.network.model.posts

data class PostsResponse(
    val _meta: Meta,
    val result: List<Result>
)