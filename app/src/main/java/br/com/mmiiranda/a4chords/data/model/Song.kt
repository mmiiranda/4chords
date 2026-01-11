package br.com.mmiiranda.a4chords.data.model


import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class Song(
    @DocumentId
    val id: String = "",
    val title: String = "",
    val artistId: String = "",
    val artistName: String = "",
    val chords: String = "",
    val lyrics: String = "",
    val capo: Int = 0,
    val key: String = "C",
    val tempo: Int? = null, // BPM
    val timeSignature: String = "4/4",

    val youtubeUrl: String? = null,
    val spotifyUrl: String? = null,
    val chordDiagramUrls: List<String> = emptyList(),

    val submittedBy: String = "", // User ID
    val submittedByName: String = "",

    @ServerTimestamp
    val submittedAt: Timestamp? = null,

    @ServerTimestamp
    val lastUpdated: Timestamp? = null,

    val isApproved: Boolean = false,
    val approvedBy: String? = null,

    val views: Long = 0,
    val favorites: Long = 0,

    val tags: List<String> = emptyList(),
    val categories: List<SongCategory> = emptyList(),
    val chordSequence: List<String> = emptyList(),

    val duration: Int? = null,
    val year: Int? = null,
    val album: String? = null,
    val language: String = "PT"
) {
    val isPopular: Boolean
        get() = favorites > 1000

    val estimatedPlayTime: String
        get() = duration?.let {
            val minutes = it / 60
            val seconds = it % 60
            "$minutes:${seconds.toString().padStart(2, '0')}"
        } ?: "--:--"
}

enum class SongCategory {
    POPULAR,
    HAWAIIAN,
    CLASSIC,
    ROCK,
    JAZZ,
    FOLK,
    BRAZILIAN,
    CHRISTMAS,
    BEGINNER_FRIENDLY,
    FOR_KIDS
}