package br.com.mauricio.a4chords.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.mauricio.a4chords.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"  // ← Agora começa no login
    ) {
        // Tela de Login
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onRegisterClick = { /* Pode abrir modal ou outra tela */ },
                onSkipLogin = { navController.navigate("home") }
            )
        }

        // Tela Inicial
        composable("home") {
            HomeScreen(
                onSongClick = { navController.navigate("song/$it") },
                onSearchClick = { navController.navigate("search") },
                onAddClick = { navController.navigate("add") },
                onProfileClick = { navController.navigate("profile") }  // ← Adicione este parâmetro
            )
        }

        // Detalhe da Música
        composable("song/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            SongScreen(
                songTitle = title,
                onBackClick = { navController.popBackStack() },
                onArtistClick = { navController.navigate("artist/iz") }  // ← Navega para artista
            )
        }

        // Busca
        composable("search") {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onSongClick = { navController.navigate("song/$it") }
            )
        }

        // Adicionar Música
        composable("add") {
            AddScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        // Perfil
        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onSongClick = { navController.navigate("song/$it") },
                onLogout = { navController.navigate("login") }
            )
        }

        // Artista
        composable("artist/{id}") { backStackEntry ->
            val artistId = backStackEntry.arguments?.getString("id") ?: ""
            ArtistScreen(
                artistId = artistId,
                onBackClick = { navController.popBackStack() },
                onSongClick = { navController.navigate("song/$it") }
            )
        }
    }
}