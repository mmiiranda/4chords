package br.com.mmiiranda.a4chords.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp


data class User(
    @DocumentId
    val id: String = "",
    val email: String = "",
    val displayName: String = "",
    val profilePictureUrl: String? = null,
    val bio: String = "",
    val favoriteSongs: List<String> = emptyList(),
    val submittedSongs: List<String> = emptyList(),

    @ServerTimestamp
    val createdAt: Timestamp? = null,

    @ServerTimestamp
    val lastLogin: Timestamp? = null,

    val isModerator: Boolean = false,
    val isAdmin: Boolean = false
) {
    fun getInitials(): String {
        return displayName.split(" ")
            .take(2)
            .joinToString("") { it.firstOrNull()?.toString() ?: "" }
            .uppercase()
    }
}