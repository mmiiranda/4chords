package br.com.mmiiranda.a4chords.utils

import android.content.Context

object ThemePrefs {
    private const val PREFS = "prefs"
    private const val DARK_MODE = "dark_mode"

    fun isDarkMode(context: Context): Boolean {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getBoolean(DARK_MODE, false)
    }

    fun setDarkMode(context: Context, enabled: Boolean) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(DARK_MODE, enabled)
            .apply()
    }
}