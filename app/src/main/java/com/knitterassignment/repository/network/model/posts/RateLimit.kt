package com.knitterassignment.repository.network.model.posts

data class RateLimit(
    val limit: Int,
    val remaining: Int,
    val reset: Int
)