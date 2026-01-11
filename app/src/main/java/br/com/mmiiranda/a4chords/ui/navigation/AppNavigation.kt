package br.com.mauricio.a4chords.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.mmiiranda.a4chords.data.model.Artist
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
                onAddClick = { navController.navigate("add") },
                onProfileClick = { navController.navigate("profile") },
                onArtistClick = { artistId ->
                    navController.navigate("artist/$artistId")
                }
            )
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onRegisterClick = {},
                onSkipLogin = { navController.navigate("home") }
            )
        }



        composable("song/{id}") { backStackEntry ->
            val songId = backStackEntry.arguments?.getString("id") ?: ""

            SongScreen(
                songTitle = songId,
                artistName = "Israel Kamakawiwo'ole",
                chordsText = """
            Intro: C - G - Am - F

            [C]Somewhere over the rainbow
            [G]Way up high
            [Am]And the dreams that you dream of
            [F]Once in a lullaby
        """.trimIndent(),
                onBackClick = { navController.popBackStack() },
                onArtistClick = { navController.navigate("artist/iz") }
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

        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.popBackStack() },
                onSongClick = { navController.navigate("song/$it") },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onAddClick = { navController.navigate("add") },
                onEditProfile = {navController.navigate("edit-profile")}
            )
        }

        composable("artist/{id}") { backStackEntry ->
            val artistId = backStackEntry.arguments?.getString("id") ?: ""

            val artist = Artist.SAMPLE_ARTISTS.firstOrNull { it.id == artistId }
                ?: Artist()

            val songs = emptyList<Song>()

            ArtistScreen(
                artist = artist,
                songs = songs,
                onBackClick = { navController.popBackStack() },
                onSongClick = { navController.navigate("song/$it") }
            )
        }

        composable("edit-profile") {
            EditProfileScreen(
                initialName = "Maurício",
                initialUsername = "@mmiiranda",
                initialBio = "Apaixonado por música 🎵",
                onBackClick = { navController.popBackStack() },
                onSaveClick = { name, username, bio ->
                    // Aqui depois você salva no Firebase
                    navController.popBackStack()
                }
            )
        }

    }
}
