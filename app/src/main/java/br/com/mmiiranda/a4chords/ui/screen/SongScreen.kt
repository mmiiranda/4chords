package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.mmiiranda.a4chords.ui.viewmodel.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongScreen(
    url: String,
    viewModel: SongViewModel,
    onBackClick: () -> Unit
) {
    val (artist, song) = viewModel.parseSongInfo(url)

    LaunchedEffect(url) {
        viewModel.loadCifra(url)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cifra $song") },
                navigationIcon = {
                    Button(onClick = onBackClick) {
                        Text("<-")
                    }
                },
                actions = {
                    Button(onClick = { viewModel.onFavoriteClick(url) }) {
                        Text(if (viewModel.isFavorite) "★" else "☆")
                    }
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (viewModel.cifra == null) {
                CircularProgressIndicator()
            } else {
                Text("Artista $artist",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = viewModel.cifra!!,
                    fontFamily = FontFamily.Monospace
                )
            }
        }
    }
}

