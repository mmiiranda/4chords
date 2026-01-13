package br.com.mmiiranda.a4chords.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CifraResponse(
    val cifra: String,
    val success: Boolean,
    val url: String
)
