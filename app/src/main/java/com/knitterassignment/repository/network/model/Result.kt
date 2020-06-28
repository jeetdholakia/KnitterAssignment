package com.knitterassignment.repository.network.model

data class Result(
    val _links: Links,
    val body: String,
    val id: String,
    val title: String,
    val user_id: String
)