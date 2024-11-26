package io.github.omydagreat.util

import moe.tlaster.precompose.navigation.Navigator

infix fun Navigator.gate(route: String) = navigate(route)
