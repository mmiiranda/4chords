package br.com.mmiiranda.a4chords.utils

fun extractChords(text: String): String {
    val regex = "\\[(.*?)\\]".toRegex()
    return regex.findAll(text)
        .map { it.groupValues[1] }
        .distinct()
        .joinToString("  ")
}
