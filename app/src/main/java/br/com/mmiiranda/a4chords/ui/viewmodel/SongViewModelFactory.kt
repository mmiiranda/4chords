package br.com.mmiiranda.a4chords.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.mmiiranda.a4chords.data.repository.SongRepository

class SongViewModelFactory(
    private val repository: SongRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SongViewModel(repository) as T
    }
}
