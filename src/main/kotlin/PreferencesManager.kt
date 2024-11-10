package io.github.omydagreat

import java.util.prefs.Preferences

/** A manager class for handling application preferences using the Java Preferences API. */
class PreferencesManager {
  companion object {
    private val prefs = Preferences.userRoot().node(this::class.java.name)
    private const val LAST_OPENED_FOLDER_KEY = "lastOpenedFolder"

    fun saveLastOpenedFolder(path: String) {
      prefs.put(LAST_OPENED_FOLDER_KEY, path)
    }

    fun loadLastOpenedFolder(): String? {
      return prefs[LAST_OPENED_FOLDER_KEY, null]
    }

    fun saveScrollPosition(folderPath: String, position: Int) {
      prefs.putInt("$folderPath-scrollPosition", position)
    }

    fun loadScrollPosition(folderPath: String): Int {
      return prefs.getInt("$folderPath-scrollPosition", 0)
    }
  }
}
