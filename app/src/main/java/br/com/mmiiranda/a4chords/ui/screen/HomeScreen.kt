package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onSongClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "4Chords",
            fontSize = 32.sp,
            color = Color(0xFF2196F3), // Azul
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = onSearchClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text("🔍 Buscar Músicas")
        }

        // Botão Adicionar
        Button(
            onClick = onAddClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("➕ Adicionar Música")
        }

        // Lista de músicas
        Text(
            text = "Músicas Populares:",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn {
            items(listOf(
                "Somewhere Over the Rainbow",
                "What a Wonderful World",
                "Hallelujah",
                "Stand By Me",
                "Let It Be"
            )) { song ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onSongClick(song) },
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = song,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = when(song) {
                                "Somewhere Over the Rainbow" -> "Israel Kamakawiwo'ole"
                                "What a Wonderful World" -> "Louis Armstrong"
                                "Hallelujah" -> "Leonard Cohen"
                                "Stand By Me" -> "Ben E. King"
                                else -> "The Beatles"
                            },
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}