package com.knitterassignment.repository.network.model.posts

import com.knitterassignment.repository.network.model.posts.Links

data class Result(
    val _links: Links,
    val body: String,
    val id: String,
    val title: String,
    val user_id: String
)