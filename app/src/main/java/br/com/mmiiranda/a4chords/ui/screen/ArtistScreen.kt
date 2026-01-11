package br.com.mmiiranda.a4chords.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ArtistScreen(
    artistId: String,
    onBackClick: () -> Unit,
    onSongClick: (String) -> Unit
) {
    val artist = when (artistId) {
        "iz" -> ArtistData(
            name = "Israel Kamakawiwo'ole",
            bio = "Cantor, compositor e músico havaiano. Ficou mundialmente famoso por sua versão de 'Somewhere Over the Rainbow/What a Wonderful World'. Conhecido carinhosamente como 'Iz', sua música continua inspirando pessoas ao redor do mundo.",
            songs = listOf(
                "Somewhere Over the Rainbow",
                "What a Wonderful World",
                "White Sandy Beach",
                "Hawaiʻi 78",
                "Over the Rainbow"
            ),
            followers = "1.2M"
        )
        else -> ArtistData(
            name = "Artista",
            bio = "Descrição do artista...",
            songs = listOf("Música 1", "Música 2", "Música 3"),
            followers = "100K"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagem de capa do artista
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Placeholder - você pode substituir por imagem real
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF2196F3).copy(alpha = 0.8f))
            )

            // Botão Voltar sobre a imagem
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.Black.copy(alpha = 0.3f), CircleShape)
            ) {
                Text("←", color = Color.White, fontSize = 20.sp)
            }
        }

        // Conteúdo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Nome do artista
            Text(
                text = artist.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Seguidores
            Text(
                text = "🎵 ${artist.followers} seguidores",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Biografia
            Card(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Sobre",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = artist.bio,
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Músicas do artista
            Text(
                text = "Músicas Populares",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyColumn {
                items(artist.songs) { song ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onSongClick(song) },
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color(0xFFE3F2FD), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "♪",
                                    fontSize = 18.sp,
                                    color = Color(0xFF2196F3)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = song, fontSize = 16.sp)
                                Text(
                                    text = "Dificuldade: Fácil • 4 acordes",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            IconButton(onClick = { /* Favoritar */ }) {
                                Text("❤️", fontSize = 18.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Seguir artista */ },
                modifier = Modifier.fillMaxWidth(),

            ) {
                Text("➕ Seguir Artista")
            }
        }
    }
}

data class ArtistData(
    val name: String,
    val bio: String,
    val songs: List<String>,
    val followers: String
)