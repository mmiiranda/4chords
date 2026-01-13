package br.com.mmiiranda.a4chords.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SongDto(
    val artist: String,
    val name: String,
    val url: String
)