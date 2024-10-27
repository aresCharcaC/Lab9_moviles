package com.example.lab09.ui.screen.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab09.domain.model.Post

@Composable
fun PostsScreen(
    viewModel: PostsViewModel,
    onPostClick: (Int) -> Unit
) {
    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        // Mostrar loading
        CircularProgressIndicator()
    } else {
        LazyColumn {
            items(posts) { post ->
                PostItem(
                    post = post,
                    onPostClick = onPostClick
                )
            }
        }
    }
}

@Composable
private fun PostItem(
    post: Post,
    onPostClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onPostClick(post.id) }
    ) {
        Text(text = post.id.toString(), Modifier.weight(0.05f))
        Spacer(Modifier.padding(horizontal = 1.dp))
        Text(text = post.title, Modifier.weight(0.7f))
        IconButton(
            onClick = { onPostClick(post.id) },
            modifier = Modifier.weight(0.1f)
        ) {
            Icon(Icons.Outlined.Search, "Ver detalle")
        }
    }
}