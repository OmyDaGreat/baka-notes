package io.github.omydagreat.util

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.malefic.prefs.collection.PersistentHashMap
import xyz.malefic.prefs.delegate.BooleanPreference
import xyz.malefic.prefs.delegate.nullable.NullableStringPreference

/**
 * PreferencesManager is a utility class that manages application preferences. It uses Kotlin's
 * StateFlow to handle dynamic updates to the list of latest files.
 */
class PreferencesManager {
  companion object {
    // Preference for the last opened folder
    var lastOpenedFolderPref by NullableStringPreference("lastOpenedFolder", null)

    // Preference for the latest files
    private var latestFilesPref by NullableStringPreference("latestFiles", null)

    // Maximum number of latest files to keep track of
    private const val MAX_LATEST_FILES = 10

    // Preference to hide hidden folders
    var hideHiddenFoldersPref by BooleanPreference("hideHiddenFolders", true)

    // Preference for dark theme
    var darkThemePref by BooleanPreference("darkTheme", false)

    // Preference for scroll positions in a persistent hash map
    var scrollPositionPref = PersistentHashMap<String, Int>("scrollPosition")

    // MutableStateFlow to hold the list of latest files
    private val _latestFilesFlow = MutableStateFlow(loadLatestFiles())

    // StateFlow to expose the latest files as a read-only flow
    val latestFilesFlow: StateFlow<List<String>>
      get() = _latestFilesFlow

    /**
     * Saves a file to the list of latest files. Updates the MutableStateFlow with the new list.
     *
     * @param file The file to be saved.
     */
    fun saveLatestFile(file: String) {
      val latestFiles =
        loadLatestFiles().toMutableList().apply {
          remove(file)
          add(0, file)
          if (size > MAX_LATEST_FILES) removeLast()
          latestFilesPref = joinToString(",")
        }
      _latestFilesFlow.value = latestFiles
    }

    /**
     * Loads the list of latest files from preferences.
     *
     * @return A list of latest file paths.
     */
    fun loadLatestFiles(): List<String> =
      (latestFilesPref ?: "").split(",").filter { it.isNotEmpty() }
  }
}
