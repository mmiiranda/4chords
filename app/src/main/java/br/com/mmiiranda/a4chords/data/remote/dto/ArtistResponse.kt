package br.com.mmiiranda.a4chords.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class ArtistResponse(
    val artist: String,
    val count: Int,
    val songs: List<SongDto>
)


