package dev.josepatino.liftheavy

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

sealed class LiftHeavyScreen(val route: String, val icon: ImageVector) {
    object Home : LiftHeavyScreen("home", Icons.Filled.Home)
    object ActiveWorkout : LiftHeavyScreen("active_workout", Icons.Filled.PlayArrow)
    object Exercises : LiftHeavyScreen("exercises", Icons.Filled.Email)
}
