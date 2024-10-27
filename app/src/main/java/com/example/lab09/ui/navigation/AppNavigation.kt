package com.example.lab09.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lab09.ui.screen.home.HomeScreen
import com.example.lab09.ui.screen.post_detail.PostDetailScreen
import com.example.lab09.ui.screen.post_detail.PostDetailViewModel
import com.example.lab09.ui.screen.posts.PostsScreen
import com.example.lab09.ui.screen.posts.PostsViewModel

sealed class Screen(val route: String) {
    object Home : Screen("inicio")
    object Posts : Screen("posts")
    object PostDetail : Screen("postsVer/{id}") {
        fun createRoute(id: Int) = "postsVer/$id"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    postsViewModel: PostsViewModel,
    postDetailViewModel: PostDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Posts.route) {
            PostsScreen(
                viewModel = postsViewModel,
                onPostClick = { id ->
                    navController.navigate(Screen.PostDetail.createRoute(id))
                }
            )
        }
        composable(
            Screen.PostDetail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val postId = backStackEntry.arguments?.getInt("id") ?: return@composable
            PostDetailScreen(
                viewModel = postDetailViewModel,
                postId = postId
            )
        }
    }
}