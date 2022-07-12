package dev.josepatino.liftheavy.extensions

import androidx.compose.ui.text.capitalize


fun String.capitalizeEachWord() =
    split(" ").joinToString(" ") { it.replaceFirstChar { it.uppercase() } }

fun String.convertToHttps() = replace(":", "s:")