package dev.josepatino.liftheavy.repository

import dev.josepatino.liftheavy.api.ExerciseDbApi
import dev.josepatino.liftheavy.ui.screens.exercises.model.ResponseExercise
import javax.inject.Inject

class ExerciseDbRepository @Inject constructor(
    private val exerciseDbApi: ExerciseDbApi
) {

    suspend fun getExercisesByBodyPart(bodyPart: String): List<ResponseExercise> {
        return exerciseDbApi.getExercisesByBodyPart(bodyPart = bodyPart)
    }
}