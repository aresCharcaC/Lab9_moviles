package com.example.lab09.ui.screen.post_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PostDetailScreen(
    viewModel: PostDetailViewModel,
    postId: Int
) {
    val post by viewModel.post.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(postId) {
        viewModel.loadPost(postId)
    }

    if (isLoading) {
        CircularProgressIndicator()
    } else {
        post?.let { post ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
            ) {
                PostDetailField(label = "ID", value = post.id.toString())
                PostDetailField(label = "User ID", value = post.userId.toString())
                PostDetailField(label = "Title", value = post.title)
                PostDetailField(label = "Body", value = post.body)
            }
        }
    }
}

@Composable
private fun PostDetailField(label: String, value: String) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
}