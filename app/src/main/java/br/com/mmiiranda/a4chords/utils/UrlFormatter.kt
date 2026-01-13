package br.com.mmiiranda.a4chords.utils

object UrlFormatter {


    fun formatArtistForApi(artistName: String): String {
        return artistName
            .trim()
            .lowercase()
            .replace("ã", "a")
            .replace("õ", "o")
            .replace("á", "a")
            .replace("é", "e")
            .replace("í", "i")
            .replace("ó", "o")
            .replace("ú", "u")
            .replace("ç", "c")
            .replace("â", "a")
            .replace("ê", "e")
            .replace("î", "i")
            .replace("ô", "o")
            .replace("û", "u")
            .replace("/", "-")
            .replace("\\s+".toRegex(), "-")
            .replace("[^a-z0-9\\-]".toRegex(), "")
            .replace("-+".toRegex(), "-")
            .removePrefix("-")
            .removeSuffix("-")
    }
}