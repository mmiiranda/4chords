package br.com.mmiiranda.a4chords.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity
import br.com.mmiiranda.a4chords.data.repository.SongRepository
import br.com.mmiiranda.a4chords.utils.UrlFormatter
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
                // Formata o artista para o formato que sua API espera
                val formattedArtist = UrlFormatter.formatArtistForApi(artist)

                Log.d("API_DEBUG", "Buscando artista:")
                Log.d("API_DEBUG", "  Original: '$artist'")
                Log.d("API_DEBUG", "  Formatado: '$formattedArtist'")

                // Chama o repositório com o artista formatado
                val songs = repository.getSongsByArtist(formattedArtist)
                uiState = SearchState.Success(songs)

                Log.d("API_DEBUG", "Resultado: ${songs.size} músicas encontradas")
            } catch (e: Exception) {
                Log.e("API_DEBUG", "Erro ao buscar artista: ${e.message}")
                uiState = SearchState.Error
            }
        }
    }

    fun clearSearch() {
        uiState = SearchState.Idle
    }
}

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    object Error : SearchState()
    data class Success(val songs: List<SongEntity>) : SearchState()
}