package br.com.mauricio.a4chords.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.mmiiranda.a4chords.data.local.AppDatabase
import br.com.mmiiranda.a4chords.data.remote.CifraApi
import br.com.mmiiranda.a4chords.data.remote.NetworkModule
import br.com.mmiiranda.a4chords.data.repository.SongRepository
import br.com.mmiiranda.a4chords.ui.screen.*
import br.com.mmiiranda.a4chords.ui.viewmodel.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val database = AppDatabase.getInstance(context)
    val repository = SongRepository(
        api = NetworkModule.cifraApi,
        dao = database.songDao()
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        // 🏠 HOME
        composable("home") {
            val viewModel: SongViewModel = viewModel(
                factory = SongViewModelFactory(repository)
            )

            // Carrega favoritas quando a tela abrir
            LaunchedEffect(Unit) {
                viewModel.loadFavorites()
            }

            HomeScreen(
                viewModel = viewModel,
                onSearchClick = { navController.navigate("search") },
                onSongClick = { url -> navController.navigate("song?url=$url") },
                onEditSongClick = { song ->
                    if (song != null) {
                        navController.navigate("edit_song?url=${song.url}")
                    } else {
                        navController.navigate("edit_song") // cria nova cifra
                    }
                },
                onSettingsClick = { navController.navigate("settings") },
            )
        }

        // 🔎 SEARCH
        composable("search") {
            val viewModel: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(repository)
            )

            SearchScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onSongClick = { url ->
                    navController.navigate("song?url=$url")
                }
            )
        }

        // 🎵 SONG / CIFRA
        composable(
            route = "song?url={url}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val url = backStackEntry.arguments?.getString("url") ?: return@composable

            val viewModel: SongViewModel = viewModel(
                factory = SongViewModelFactory(repository)
            )

            SongScreen(
                url = url,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )

        }

        composable(
            route = "edit_song?url={url}",
            arguments = listOf(
                navArgument("url") { defaultValue = "" } // vazio = criar nova
            )
        ) { backStackEntry ->

            val url = backStackEntry.arguments?.getString("url").orEmpty()
            val viewModel: SongViewModel = viewModel(
                factory = SongViewModelFactory(repository)
            )

            LaunchedEffect(url) {
                if (url.isNotEmpty()) {
                    viewModel.loadCifra(url)
                }
            }

            EditSongScreen(
                song = viewModel.currentSong,
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("settings") {
            val themeViewModel: ThemeViewModel = viewModel()
            SettingsScreen(
                themeViewModel = themeViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

    }
}
