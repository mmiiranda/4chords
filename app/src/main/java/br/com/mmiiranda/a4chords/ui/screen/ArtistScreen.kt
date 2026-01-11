package br.com.mmiiranda.a4chords.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.com.mmiiranda.a4chords.data.model.Artist

import br.com.mmiiranda.a4chords.ui.components.SongCard


@Composable
fun ArtistScreen(
    artist: Artist,
    songs: List<Song>,
    onBackClick: () -> Unit,
    onSongClick: (String) -> Unit
) {
    val primaryColor = Color(0xFF00897B)
    val backgroundColor = Color(0xFFF4FAF8)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        // TopBar simples
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "←",
                fontSize = 20.sp,
                modifier = Modifier
                    .clickable { onBackClick() }
                    .padding(end = 16.dp)
            )

            Text(
                text = "Artistas",
                fontWeight = FontWeight.Medium
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // Avatar + nome
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(primaryColor.copy(alpha = 0.15f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = artist.name.take(1),
                        fontSize = 28.sp,
                        color = primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = artist.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    if (artist.genres.isNotEmpty()) {
                        Text(
                            text = artist.genres.joinToString(" / "),
                            fontSize = 12.sp,
                            color = Color.White,
                            modifier = Modifier
                                .background(primaryColor, RoundedCornerShape(50))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = artist.bio,
                fontSize = 14.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${artist.totalSongs} cifras disponíveis",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Cifras",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(songs) { song ->
                    SongCard(
                        song = song,
                        onClick = { onSongClick(song.title) }
                    )
                }
            }
        }
    }
}
