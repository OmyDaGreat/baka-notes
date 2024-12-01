package io.github.omydagreat.util

import xyz.malefic.prefs.BooleanPreference
import xyz.malefic.prefs.SerializablePreference
import xyz.malefic.prefs.nullable.NullableStringPreference

/** A manager class for handling application preferences using the Java Preferences API. */
class PreferencesManager {
  companion object {
    var lastOpenedFolder by NullableStringPreference("lastOpenedFolder", null)
    private var latestFiles by NullableStringPreference("latestFiles", null)
    private const val MAX_LATEST_FILES = 10
    var hideHiddenFoldersPref by BooleanPreference("hideHiddenFolders", true)
    var scrollPosition by SerializablePreference<HashMap<String, Int>>("scrollPositionKey", hashMapOf())

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
        latestFiles = joinToString(",")
      }

    /**
     * Loads the list of latest files from the preferences.
     *
     * @return A list of file paths.
     */
    fun loadLatestFiles(): List<String> = (latestFiles ?: "").split(",").filter { it.isNotEmpty() }
  }
}
