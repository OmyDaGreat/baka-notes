package io.github.omydagreat.util

import java.io.File

/**
 * Data class representing a tree node.
 *
 * @param T The type of the value.
 * @param value The value of the node.
 * @param children The children of the node.
 */
data class TreeNode<T>(val value: T, val children: List<TreeNode<T>> = emptyList())

/**
 * Recursively builds a file tree from a given file.
 *
 * This function traverses the directory structure starting from the given root file and constructs
 * a tree where each node represents a file or directory. The root node represents the initial file,
 * and its children represent the files and directories contained within it. This process is
 * repeated recursively for each directory.
 *
 * @return The root node of the file tree, where each node contains a file and its children.
 * @receiver The root file to build the tree from.
 */
fun File.buildFileTree(): TreeNode<File> =
  TreeNode(this, listFiles()?.map { it.buildFileTree() } ?: emptyList())

/**
 * Flattens a tree structure into a list of pairs.
 *
 * @param node The root node of the tree.
 * @param depth The current depth of the node.
 * @return A list of pairs containing the file and its depth.
 */
fun flattenTree(node: TreeNode<File>, depth: Int = 0): List<Pair<File, Int>> =
  mutableListOf<Pair<File, Int>>().also {
    it.add(node.value to depth)
    node.children.forEach { child -> it.addAll(flattenTree(child, depth + 1)) }
  }

/**
 * Filters the tree nodes based on a given predicate.
 *
 * This function recursively filters the tree nodes. If a node does not satisfy the predicate, it
 * will be excluded from the resulting tree.
 *
 * @param predicate The condition to filter the nodes.
 * @return The filtered tree node or null if the node does not satisfy the predicate.
 */
fun TreeNode<File>.filter(predicate: (File) -> Boolean): TreeNode<File>? {
  if (!predicate(this.value)) return null
  val filteredChildren = this.children.mapNotNull { it.filter(predicate) }
  return TreeNode(this.value, filteredChildren)
}
