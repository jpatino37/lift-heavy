package dev.josepatino.liftheavy.networking

import dev.josepatino.liftheavy.model.ResponseExerciseList
import retrofit2.Response
import retrofit2.http.GET

interface ExerciseDbApi {

    companion object {
        const val ExerciseDbApiBaseUrl = "https://exercisedb.p.rapidapi.com/exercises"
    }
}