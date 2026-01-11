package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSongClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit,
    onProfileClick: () -> Unit,
    onArtistClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "4Chords",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    Text("Perfil")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text("Adicionar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Botão de busca
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable(onClick = onSearchClick),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Buscar músicas ou artistas...",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                }
            }

            // Seção: Destaques
            Text(
                text = "🎵 Destaques da Semana",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
            )

            // Carrossel de destaques (simplificado)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Destaque 1
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .clickable { onArtistClick("iz") },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "🎸",
                            fontSize = 32.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Israel 'IZ'",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Artista Havaiano",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }

                // Destaque 2
                Card(
                    modifier = Modifier
                        .width(160.dp)
                        .clickable { onSongClick("Hallelujah") },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "⭐",
                            fontSize = 32.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Para Iniciantes",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Músicas fáceis",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            // Seção: Músicas Populares
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🎶 Músicas Populares",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(onClick = { /* Ver todas */ }) {
                    Text("Ver todas")
                }
            }

            // Lista de músicas
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(getPopularSongs()) { song ->
                    SongCard(
                        song = song,
                        onClick = { onSongClick(song.title) }
                    )
                }
            }
        }
    }
}

@Composable
fun SongCard(
    song: Song,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Ícone da música
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("🎸", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Informações
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = song.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = song.artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )

                // Tags
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AssistChip(
                        onClick = {},
                        label = { Text(song.difficulty) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = when (song.difficulty) {
                                "Fácil" -> Color(0xFF4CAF50).copy(alpha = 0.2f)
                                "Médio" -> Color(0xFFFF9800).copy(alpha = 0.2f)
                                else -> Color(0xFFF44336).copy(alpha = 0.2f)
                            },
                            labelColor = when (song.difficulty) {
                                "Fácil" -> Color(0xFF4CAF50)
                                "Médio" -> Color(0xFFFF9800)
                                else -> Color(0xFFF44336)
                            }
                        )
                    )
                    AssistChip(
                        onClick = {},
                        label = { Text("${song.chords} acordes") },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
                            labelColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                }
            }

        }
    }
}

// Data class para músicas
data class Song(
    val title: String,
    val artist: String,
    val difficulty: String,
    val chords: Int,
    val favorites: Int
)

// Função para obter músicas populares
fun getPopularSongs(): List<Song> {
    return listOf(
        Song("Somewhere Over the Rainbow", "Israel Kamakawiwo'ole", "Fácil", 4, 1250),
        Song("What a Wonderful World", "Louis Armstrong", "Fácil", 3, 980),
        Song("Hallelujah", "Leonard Cohen", "Médio", 5, 2100),
        Song("Stand By Me", "Ben E. King", "Fácil", 4, 1560),
        Song("Let It Be", "The Beatles", "Médio", 5, 1890),
        Song("Imagine", "John Lennon", "Fácil", 3, 2340),
        Song("Blowing in the Wind", "Bob Dylan", "Fácil", 4, 1120),
        Song("Hotel California", "Eagles", "Difícil", 7, 1980)
    )
}