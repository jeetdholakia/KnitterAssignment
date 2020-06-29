package com.knitterassignment.repository

import com.knitterassignment.repository.db.comments.Comment

interface CommentsRepository {

    suspend fun getComments(): List<Comment>?
}