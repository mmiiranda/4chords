package br.com.mmiiranda.a4chords.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity
import br.com.mmiiranda.a4chords.data.repository.SongRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: SongRepository
) : ViewModel() {

    var uiState by mutableStateOf<SearchState>(SearchState.Idle)
        private set

    fun searchArtist(artist: String) {
        viewModelScope.launch {
            uiState = SearchState.Loading
            try {
                Log.d("API_DEBUG", "Buscando artista: $artist")
                val songs = repository.getSongsByArtist(artist)
                uiState = SearchState.Success(songs)
            } catch (e: Exception) {
                uiState = SearchState.Error
            }
        }
    }
}

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    object Error : SearchState()
    data class Success(val songs: List<SongEntity>) : SearchState()
}
