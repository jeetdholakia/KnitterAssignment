package com.knitterassignment.repository

import android.content.Context
import com.knitterassignment.repository.db.posts.Post
import com.knitterassignment.repository.db.posts.PostsDatabase
import com.knitterassignment.repository.network.PostsService
import com.knitterassignment.util.NetworkUtil
import com.orhanobut.logger.Logger

class PostsRepositoryImpl(var context: Context): PostsRepository {

    private val postsDB by lazy { PostsDatabase(
        context
    ).postsDAO() }
    private val postsService =  PostsService.getPostsService()
    //private val commentsService = CommentsService.getCommentsService()
    private var pageNumber: Int = 0


    override suspend fun getPosts(): List<Post>? {
        return if(NetworkUtil.isOnline(context)) {
            Logger.d("Device is online")
            val response = postsService.getPosts(pageNumber++)
            val result =  response.result
//            val commentsResponse = commentsService.getComments(1)
//            val commentsResult = commentsResponse.result
            pageNumber = response._meta.currentPage
//            val commentList = mutableListOf<Comment>()
//
//            for(comment in commentsResult) {
//                val newComment = Comment(comment.id, comment.post_id, comment.name, comment.email, comment.body)
//                commentList.add(newComment)
//            }

            for (post in result) {
                val newPost = Post(
                    post.id,
                    post.user_id,
                    post.title,
                    post.body
                )
                postsDB.insertNewPost(newPost)
            }
            return postsDB.getPosts()
        } else {
            Logger.d("Device is offline")
            if(postsDB.getPosts().isNotEmpty()) {
                Logger.d("Some data is present in offline state")
                return postsDB.getPosts()
            } else {
                Logger.d("No data is present in offline state, show an error here")
                null
            }

        }
    }
}