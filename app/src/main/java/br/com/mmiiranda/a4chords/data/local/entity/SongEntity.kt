package br.com.mmiiranda.a4chords.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey val url: String,
    val artist: String,
    val name: String,
    val cifra: String? = null
)
