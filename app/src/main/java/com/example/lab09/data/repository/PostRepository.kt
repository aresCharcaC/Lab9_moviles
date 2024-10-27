package com.example.lab09.data.repository

import com.example.lab09.data.remote.PostApiService
import com.example.lab09.domain.model.Post

class PostRepository(private val apiService: PostApiService) {
    suspend fun getPosts(): List<Post> {
        return apiService.getUserPosts().map { postModel ->
            Post(
                userId = postModel.userId,
                id = postModel.id,
                title = postModel.title,
                body = postModel.body
            )
        }
    }

    suspend fun getPostById(id: Int): Post {
        val postModel = apiService.getUserPostById(id)
        return Post(
            userId = postModel.userId,
            id = postModel.id,
            title = postModel.title,
            body = postModel.body
        )
    }
}