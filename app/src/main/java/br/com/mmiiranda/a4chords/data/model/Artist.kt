package br.com.mmiiranda.a4chords.data.model


import com.google.firebase.firestore.DocumentId

data class Artist(
    @DocumentId
    val id: String = "",
    val name: String = "",
    val bio: String = "",
    val profilePictureUrl: String? = null,
    val bannerUrl: String? = null,
    val genres: List<String> = emptyList(),
    val nationality: String = "",
    val yearsActive: String = "",
    val totalSongs: Int = 0,
    val totalFollowers: Int = 0,
    val website: String? = null,
    val youtubeChannel: String? = null,
    val spotifyId: String? = null,
    val isVerified: Boolean = false
) {
    companion object {
        val SAMPLE_ARTISTS = listOf(
            Artist(
                id = "iz_kamakawiwoole",
                name = "Israel Kamakawiwo'ole",
                bio = "Cantor, compositor e músico havaiano conhecido por 'Somewhere Over the Rainbow'.",
                genres = listOf("Hawaiian", "Folk", "Reggae"),
                nationality = "Hawaiian",
                totalSongs = 15,
                totalFollowers = 1250000,
                isVerified = true
            ),
            Artist(
                id = "jake_shimabukuro",
                name = "Jake Shimabukuro",
                bio = "Virtuoso do ukulele conhecido por suas versões de 'While My Guitar Gently Weeps'.",
                genres = listOf("Instrumental", "Rock", "Jazz"),
                nationality = "Japanese-American",
                totalSongs = 28,
                totalFollowers = 850000,
                isVerified = true
            )
        )
    }
}