package br.com.mmiiranda.a4chords.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mmiiranda.a4chords.data.repository.SongRepository
import kotlinx.coroutines.launch

class SongViewModel(
    private val repository: SongRepository
) : ViewModel() {

    var cifra by mutableStateOf<String?>(null)
        private set

    fun loadCifra(url: String) {
        viewModelScope.launch {
            cifra = repository.getCifra(url).cifra
        }
    }

    fun parseSongInfo(url: String): Pair<String, String> {
        val parts = url
            .removePrefix("https://www.cifraclub.com.br/")
            .trim('/')
            .split('/')

        val artist = parts.getOrNull(0)
            ?.replace("-", " ")
            ?.replaceFirstChar { it.uppercase() }
            ?: ""

        val song = parts.getOrNull(1)
            ?.replace("-", " ")
            ?.replaceFirstChar { it.uppercase() }
            ?: ""

        return artist to song
    }

}

