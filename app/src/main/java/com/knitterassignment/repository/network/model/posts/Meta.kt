package com.knitterassignment.repository.network.model.posts

data class Meta(
    val code: Int,
    val currentPage: Int,
    val message: String,
    val pageCount: Int,
    val perPage: Int,
    val rateLimit: RateLimit,
    val success: Boolean,
    val totalCount: Int
)