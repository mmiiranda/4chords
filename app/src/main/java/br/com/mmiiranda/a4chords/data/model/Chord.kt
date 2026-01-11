package br.com.mmiiranda.a4chords.data.model


data class Chord(
    val name: String,
    val diagram: ChordDiagram? = null,
    val alternativeNames: List<String> = emptyList(),
    val difficulty: ChordDifficulty = ChordDifficulty.EASY,
    val description: String = ""
)

data class ChordDiagram(
    val fingering: String,
    val positions: List<FingerPosition>,
    val baseFret: Int = 0
)

data class FingerPosition(
    val string: Int,
    val fret: Int,
    val finger: Int? = null
)

// Dificuldade do acorde
enum class ChordDifficulty {
    EASY,      // Ex: C, G, F, Am
    MEDIUM,    // Ex: Bm, D7, E7
    HARD,      // Ex: F#m, Bb, barre chords

}

object BasicChords {
    val COMMON_CHORDS = listOf(
        Chord("C", difficulty = ChordDifficulty.EASY),
        Chord("G", difficulty = ChordDifficulty.EASY),
        Chord("F", difficulty = ChordDifficulty.EASY),
        Chord("Am", difficulty = ChordDifficulty.EASY),
        Chord("Dm", difficulty = ChordDifficulty.MEDIUM),
        Chord("Em", difficulty = ChordDifficulty.MEDIUM),
        Chord("A", difficulty = ChordDifficulty.MEDIUM),
        Chord("D", difficulty = ChordDifficulty.MEDIUM),
        Chord("E", difficulty = ChordDifficulty.HARD),
        Chord("Bm", difficulty = ChordDifficulty.HARD)
    )
}