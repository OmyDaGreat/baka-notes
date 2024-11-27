package io.github.omydagreat.util

import java.util.prefs.Preferences

/** A manager class for handling application preferences using the Java Preferences API. */
class PreferencesManager {
  companion object {
    private val prefs = Preferences.userRoot().node(this::class.java.name)
    private const val LAST_OPENED_FOLDER_KEY = "lastOpenedFolder"
    private const val LATEST_FILES_KEY = "latestFiles"
    private const val MAX_LATEST_FILES = 10
    private const val HIDE_HIDDEN_FOLDERS_KEY = "hideHiddenFolders"

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
    fun loadScrollPosition(folderPath: String) = prefs.getInt("$folderPath-scrollPosition", 0)

    /**
     * Saves a single file to the list of latest files in the preferences.
     *
     * @param file The file to save.
     */
    fun saveLatestFile(file: String) =
      loadLatestFiles().toMutableList().apply {
        remove(file)
        add(0, file)
        if (size > MAX_LATEST_FILES) removeLast()
        prefs.put(LATEST_FILES_KEY, joinToString(","))
      }

    /**
     * Loads the list of latest files from the preferences.
     *
     * @return A list of file paths.
     */
    fun loadLatestFiles(): List<String> =
      prefs[LATEST_FILES_KEY, ""].split(",").filter { it.isNotEmpty() }

    /**
     * Saves the state of whether hidden folders should be hidden to the preferences.
     *
     * @param hide `true` to hide hidden folders, `false` otherwise.
     */
    fun saveHideHiddenFoldersState(hide: Boolean) = prefs.putBoolean(HIDE_HIDDEN_FOLDERS_KEY, hide)

    /**
     * Loads the state of whether hidden folders should be hidden from the preferences.
     *
     * @return `true` if hidden folders should be hidden, `false` otherwise.
     */
    fun loadHideHiddenFoldersState(): Boolean = prefs.getBoolean(HIDE_HIDDEN_FOLDERS_KEY, true)
  }
}
