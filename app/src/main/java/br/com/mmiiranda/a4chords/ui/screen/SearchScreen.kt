package br.com.mmiiranda.a4chords.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onSongClick: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Cabeçalho
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBackClick,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text("← Voltar")
            }

            Text(
                text = "Buscar",
                color = Color(0xFF2196F3)
            )
        }

        // Campo de busca
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Nome da música ou artista") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchText.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Digite para buscar músicas")
            }
        } else {
            LazyColumn {
                items(listOf(
                    "Over the Rainbow - Israel Kamakawiwo'ole",
                    "Rainbow - Kacey Musgraves",
                    "Rainbow Connection - Kermit",
                    "Set Fire to the Rain - Adele",
                    "Purple Rain - Prince"
                ).filter { it.contains(searchText, ignoreCase = true) }) { result ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onSongClick(result) },
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = result.split(" - ")[0],

                            )
                            Text(
                                text = result.split(" - ")[1],
                            )
                        }
                    }
                }
            }
        }
    }
}