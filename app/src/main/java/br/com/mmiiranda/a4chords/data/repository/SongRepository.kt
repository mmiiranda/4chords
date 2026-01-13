package br.com.mmiiranda.a4chords.data.repository

import android.util.Log
import br.com.mmiiranda.a4chords.data.local.dao.SongDao
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity
import br.com.mmiiranda.a4chords.data.remote.CifraApi

class SongRepository(
    private val api: CifraApi,
    private val dao: SongDao
) {

    suspend fun getFavoriteSongs() :  List<SongEntity>{
        return dao.getFavoriteSongs()
    }

    suspend fun updateSong(song: SongEntity) {
        dao.insertSong(song)
    }

    suspend fun insertOrUpdateSong(song: SongEntity) {
        dao.insertSong(song)
    }

    suspend fun getSongsByArtist(artist: String): List<SongEntity> {

        return try {
            val remote = api.getArtist(artist)

            val entities = remote.songs.map {
                SongEntity(
                    url = it.url,
                    artist = artist,
                    name = it.name
                )
            }

            dao.insertSongs(entities)
            entities

        } catch (e: Exception) {
            Log.e("API_ERROR", "Erro ao buscar artista", e)
            emptyList()
        }
    }

    suspend fun toggleFavorite(url: String) {
        val song = dao.getSongByUrl(url) ?: return
        dao.updateFavorite(url, !song.isFavorite)
    }

    suspend fun getCifra(url: String): SongEntity {
        val local = dao.getSongByUrl(url)

        if (local?.cifra != null) {
            return local
        }

        val remote = api.getCifra(url)

        val updated = local?.copy(cifra = remote.cifra)
            ?: SongEntity(
                url = url,
                artist = "",
                name = "",
                cifra = remote.cifra
            )

        dao.insertSong(updated)
        return updated
    }
}
