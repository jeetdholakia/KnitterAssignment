package com.knitterassignment.repository

import android.content.Context
import com.knitterassignment.repository.db.comments.Comment
import com.knitterassignment.repository.db.comments.CommentDAO
import com.knitterassignment.repository.db.comments.CommentDatabase
import com.knitterassignment.repository.network.CommentsService
import com.knitterassignment.util.NetworkUtil
import com.orhanobut.logger.Logger

class CommentsRepositoryImpl(var context: Context): CommentsRepository {

    private val commentsDB: CommentDAO by lazy { CommentDatabase(
        context
    ).commentDAO() }
    private val commentsService =  CommentsService.getCommentsService()
    private var pageNumber: Int = 0

    override suspend fun getComments(): List<Comment>? {
        return if(NetworkUtil.isOnline(context)) {
            Logger.d("Device is online")
            val response = commentsService.getComments(pageNumber++)
            val result =  response.result
            pageNumber = response._meta.currentPage

            for (comment in result) {
                val newPost = Comment(
                    comment.id,
                    comment.post_id,
                    comment.name,
                    comment.email,
                    comment.body
                )
                commentsDB.insertNewComment(newPost)
            }
            return commentsDB.getComments()
        } else {
            Logger.d("Device is offline")
            if(commentsDB.getComments().isNotEmpty()) {
                Logger.d("Some data is present in offline state")
                return commentsDB.getComments()
            } else {
                Logger.d("No data is present in offline state, show an error here")
                null
            }

        }
    }
}