package io.github.omydagreat

import java.util.prefs.Preferences

class PreferencesManager {
  companion object {
    private val prefs = Preferences.userRoot().node(this::class.java.name)
    private const val LAST_OPENED_FOLDER_KEY = "lastOpenedFolder"

    fun saveLastOpenedFolder(path: String) {
      prefs.put(LAST_OPENED_FOLDER_KEY, path)
    }

    fun loadLastOpenedFolder(): String? {
      return prefs.get(LAST_OPENED_FOLDER_KEY, null)
    }
  }
}
