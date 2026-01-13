package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity
import br.com.mmiiranda.a4chords.ui.viewmodel.SongViewModel
import br.com.mmiiranda.a4chords.ui.components.SongCardMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: SongViewModel,
    onSearchClick: () -> Unit,
    onSongClick: (String) -> Unit,
    onEditSongClick: (SongEntity?) -> Unit,
    onSettingsClick: () -> Unit
) {
    val favoriteSongs = viewModel.favoriteSongs

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "4Chords",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    OutlinedButton(
                        onClick = onSearchClick,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("🔍 ")
                    }

                    OutlinedButton(onClick = onSettingsClick) {
                        Text("⚙️")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditSongClick(null) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ) {
                Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (favoriteSongs.isEmpty()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎸",
                        textAlign = TextAlign.Center,
                        fontSize = 96.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Nenhuma cifra encontrada",
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Busque por um artista para encontrar cifras ou adicione uma nova.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    FilledTonalButton(
                        onClick = onSearchClick,
                        modifier = Modifier.fillMaxWidth(0.7f)
                    ) {
                        Text("Começar a buscar", style = MaterialTheme.typography.labelLarge)
                    }
                }

            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favoriteSongs) { song ->
                        SongCardMenu(
                            song = song,
                            onSongClick = onSongClick,
                            onToggleFavorite = { viewModel.toggleFavorite(song) },
                            onEditClick = { onEditSongClick(song) }
                        )
                    }
                }
            }
        }
    }
}
