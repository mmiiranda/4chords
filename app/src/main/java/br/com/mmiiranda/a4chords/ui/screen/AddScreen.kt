package br.com.mmiiranda.a4chords.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

@Composable
fun AddScreen(
    onBackClick: () -> Unit
) {
    // Inputs
    var title by remember { mutableStateOf("") }
    var artistName by remember { mutableStateOf("") }
    var chordsText by remember { mutableStateOf("") }

    // Usuário simulado (pode trocar depois pelo FirebaseAuth)
    val currentUserId = "user_123"
    val currentUserName = "Maurício"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        // Cabeçalho simples
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "←",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .clickable { onBackClick() }
            )
            Text(
                text = "Nova Cifra",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título da música") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = artistName,
                    onValueChange = { artistName = it },
                    label = { Text("Artista") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Cifra")

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = chordsText,
                    onValueChange = { chordsText = it },
                    placeholder = {
                        Text(
                            "[C]Somewhere over the [G]rainbow\n" +
                                    "[Am]Way up [F]high"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    singleLine = false,
                    textStyle = LocalTextStyle.current.copy(
                        fontFamily = FontFamily.Monospace
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {

                        // Cria Song


                        // Debug / futuro Firestore
                        println("SUBMISSION:")


                        onBackClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    enabled = title.isNotBlank()
                            && artistName.isNotBlank()
                            && chordsText.isNotBlank()
                ) {
                    Text("📤 Enviar para revisão")
                }
            }
        }
    }
}
