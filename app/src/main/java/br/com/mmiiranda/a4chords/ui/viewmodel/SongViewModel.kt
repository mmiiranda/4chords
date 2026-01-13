package br.com.mmiiranda.a4chords.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity
import br.com.mmiiranda.a4chords.data.repository.SongRepository
import kotlinx.coroutines.launch

class SongViewModel(
    private val repository: SongRepository
) : ViewModel() {

    var cifra by mutableStateOf<String?>(null)
        private set

    var isFavorite by mutableStateOf(false)
        private set

    var favoriteSongs by mutableStateOf<List<SongEntity>>(emptyList())
        private set

    var currentSong by mutableStateOf<SongEntity?>(null)
        private set

    fun loadCifra(url: String) {
        viewModelScope.launch {
            val songEntity = repository.getCifra(url)
            cifra = songEntity.cifra
            isFavorite = songEntity.isFavorite
            currentSong = songEntity
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            favoriteSongs = repository.getFavoriteSongs()
        }
    }

    fun toggleFavorite(song: SongEntity) {
        val updated = song.copy(isFavorite = !song.isFavorite)
        viewModelScope.launch {
            repository.updateSong(updated)
            loadFavorites()
            currentSong = song.copy(isFavorite = isFavorite)
        }
    }

    fun onFavoriteClick(url: String) {
        viewModelScope.launch {
            repository.toggleFavorite(url)
            isFavorite = !isFavorite
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

    fun createOrUpdateSong(url: String, artist: String, name: String, cifraText: String) {
        val song = SongEntity(
            url = url,
            artist = artist,
            name = name,
            cifra = cifraText,
            isFavorite = true
        )
        viewModelScope.launch {
            repository.updateSong(song)
            currentSong = song
            cifra = cifraText
            isFavorite = true
        }
    }

}

