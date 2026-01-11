package br.com.mmiiranda.a4chords.data.model


import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

data class Submission(
    @DocumentId
    val id: String = "",
    val songId: String = "",
    val songData: Song = Song(),
    val type: SubmissionType = SubmissionType.NEW_SONG,
    val status: SubmissionStatus = SubmissionStatus.PENDING,

    val submittedBy: String = "",
    val submittedByName: String = "",

    @ServerTimestamp
    val submittedAt: Timestamp? = null,

    val assignedTo: String? = null, // Moderador ID
    val reviewedAt: Timestamp? = null,
    val notes: String = "",
    val rejectionReason: String? = null
)

enum class SubmissionType {
    NEW_SONG,
    SONG_EDIT,
    ARTIST_ADD,
    CHORD_CORRECTION
}

enum class SubmissionStatus {
    PENDING,
    UNDER_REVIEW,
    APPROVED,
    REJECTED,
    NEEDS_CHANGES
}