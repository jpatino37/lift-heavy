package dev.josepatino.liftheavy.ui.screens.routines.model

import dev.josepatino.liftheavy.ui.screens.exercises.model.ResponseExercise

data class Exercise(
    val bodyPart: String,
    val gifUrl: String,
    val id: String,
    val name: String,
    val expectedSets: Int,
    val expectedReps: Int
)

data class CompletedExercise(
    val bodyPart: String,
    val id: String,
    val name: String,
    val setsCompleted: Int,
    val repsCompleted: Int,
    // TODO: below value should be a LocalDateTime eventually
    val completedOn: String
)

data class Routine(
    val name: String,
    val exercises: List<Exercise>,
)
