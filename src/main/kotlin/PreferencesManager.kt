package io.github.omydagreat

import java.util.prefs.Preferences

/** A manager class for handling application preferences using the Java Preferences API. */
class PreferencesManager {
  companion object {
    private val prefs = Preferences.userRoot().node(this::class.java.name)
    private const val LAST_OPENED_FOLDER_KEY = "lastOpenedFolder"

    /**
     * Saves the path of the last opened folder to the preferences.
     *
     * @param path The path of the folder to save.
     */
    fun saveLastOpenedFolder(path: String) = prefs.put(LAST_OPENED_FOLDER_KEY, path)

    /**
     * Loads the path of the last opened folder from the preferences.
     *
     * @return The path of the last opened folder, or null if not found.
     */
    fun loadLastOpenedFolder(): String? = prefs[LAST_OPENED_FOLDER_KEY, null]

    /**
     * Saves the scroll position for a specific folder path to the preferences.
     *
     * @param folderPath The path of the folder.
     * @param position The scroll position to save.
     */
    fun saveScrollPosition(folderPath: String, position: Int) =
      prefs.putInt("$folderPath-scrollPosition", position)

    /**
     * Loads the scroll position for a specific folder path from the preferences.
     *
     * @param folderPath The path of the folder.
     * @return The scroll position, or 0 if not found.
     */
    fun loadScrollPosition(folderPath: String): Int = prefs.getInt("$folderPath-scrollPosition", 0)
  }
}
