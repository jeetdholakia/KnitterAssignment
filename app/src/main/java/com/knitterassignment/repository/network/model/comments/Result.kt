package com.knitterassignment.repository.network.model.comments

data class Result(
    val _links: Links,
    val body: String,
    val email: String,
    val id: String,
    val name: String,
    val post_id: String
)