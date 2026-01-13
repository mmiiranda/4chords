package br.com.mmiiranda.a4chords.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.mmiiranda.a4chords.ui.viewmodel.SearchState
import br.com.mmiiranda.a4chords.ui.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onSongClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val state = viewModel.uiState

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar artista") },
                navigationIcon = {
                    Button(onClick = onBackClick) {
                        Text("<-")
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
        ) {

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Ex: marina-sena") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.searchArtist(query) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Buscar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (state) {
                SearchState.Idle -> Unit
                SearchState.Loading -> {
                    CircularProgressIndicator()
                }

                SearchState.Error -> {
                    Text("Erro ao buscar artista")
                }

                is SearchState.Success -> {
                    LazyColumn {
                        items(state.songs) { song ->
                            ListItem(
                                headlineContent = { Text(song.name) },
                                modifier = Modifier.clickable {
                                    onSongClick(song.url)
                                }
                            )
                        }
                    }
                }

            }
        }
    }
}
