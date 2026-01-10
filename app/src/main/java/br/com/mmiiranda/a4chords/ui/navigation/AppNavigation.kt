package br.com.mmiiranda.a4chords.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.mmiiranda.a4chords.ui.screen.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onSongClick = { navController.navigate("song/$it") },
                onSearchClick = { navController.navigate("search") },
                onAddClick = { navController.navigate("add") }
            )
        }

        composable("song/{id}") {
            SongScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("search") {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onSongClick = { navController.navigate("song/$it") }
            )
        }

        composable("add") {
            AddScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}