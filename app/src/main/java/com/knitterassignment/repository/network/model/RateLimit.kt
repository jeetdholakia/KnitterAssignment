package com.knitterassignment.repository.network.model

data class RateLimit(
    val limit: Int,
    val remaining: Int,
    val reset: Int
)