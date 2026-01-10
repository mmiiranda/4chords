package br.com.mmiiranda.a4chords.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddScreen(
    onBackClick: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var chords by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
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
                text = "Nova Música",
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título da música") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Artista
                OutlinedTextField(
                    value = artist,
                    onValueChange = { artist = it },
                    label = { Text("Artista") },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Cifra:",
                )

                OutlinedTextField(
                    value = chords,
                    onValueChange = { chords = it },
                    placeholder = { Text("Digite os acordes aqui...\nEx: [C]Somewhere over the [G]rainbow") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    singleLine = false,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        println("Salvando: $title - $artist")
                        onBackClick()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = title.isNotBlank() && artist.isNotBlank() && chords.isNotBlank(),

                ) {
                    Text("💾 Salvar Música")
                }
            }
        }
    }
}