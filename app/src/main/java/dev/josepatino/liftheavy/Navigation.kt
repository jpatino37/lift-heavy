package dev.josepatino.liftheavy

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.josepatino.liftheavy.ui.screens.exercises.ExerciseViewModel
import dev.josepatino.liftheavy.ui.screens.exercises.Exercises
import dev.josepatino.liftheavy.ui.screens.LiftHeavyHome
import dev.josepatino.liftheavy.ui.screens.ActiveWorkout
import dev.josepatino.liftheavy.ui.screens.exercises.model.ResponseExercise

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
//    onNetworkError: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = LiftHeavyScreen.Home.route,
        modifier = modifier
    ) {
        composable(LiftHeavyScreen.Home.route) {
            LiftHeavyHome(navController = navController)
        }
        composable(LiftHeavyScreen.ActiveWorkout.route) {
            ActiveWorkout()
        }
        composable(LiftHeavyScreen.Exercises.route) {
            val vm: ExerciseViewModel = hiltViewModel()
            val exercisesList: List<ResponseExercise> by vm.filteredList.observeAsState(
                emptyList()
            )
            val searchValue: String by vm.searchVal.observeAsState("")
            Exercises(
                exercisesList,
                searchVal = searchValue,
                onValueChange = { vm.onValueChange(it) })
        }
    }
}