package br.com.mmiiranda.a4chords.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mmiiranda.a4chords.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {

    @Query("SELECT * FROM songs WHERE artist = :artist")
    suspend fun getSongsByArtist(artist: String): List<SongEntity>

    @Query("SELECT * FROM songs WHERE url = :url")
    suspend fun getSongByUrl(url: String): SongEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)
}
