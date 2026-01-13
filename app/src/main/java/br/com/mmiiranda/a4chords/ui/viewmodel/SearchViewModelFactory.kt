package br.com.mmiiranda.a4chords.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mmiiranda.a4chords.data.repository.SongRepository

class SearchViewModelFactory(
    private val repository: SongRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}
