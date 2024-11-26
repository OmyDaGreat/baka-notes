package io.github.omydagreat.util.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object Text {
  @Composable
  fun Heading1(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h1,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Heading2(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h2,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Heading3(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h3,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Heading4(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h4,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Heading5(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h5,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Heading6(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h6,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Body1(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.body1,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Body2(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.body2,
      color = MaterialTheme.colors.onBackground,
      modifier = modifier,
    )

  @Composable
  fun Heading1B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h1,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Heading2B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h2,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Heading3B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h3,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Heading4B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h4,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Heading5B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h5,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Heading6B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.h6,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Body1B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.body1,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )

  @Composable
  fun Body2B(text: String, modifier: Modifier = Modifier) =
    Text(
      text = text,
      style = typography.body2,
      color = MaterialTheme.colors.background,
      modifier = modifier,
    )
}
