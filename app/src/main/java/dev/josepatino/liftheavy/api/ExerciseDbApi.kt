package dev.josepatino.liftheavy.api

import dev.josepatino.liftheavy.ui.screens.exercises.model.ResponseExercise
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ExerciseDbApi {

    companion object {
        const val ExerciseDbApiBaseUrl = "https://exercisedb.p.rapidapi.com/exercises/"
    }

    @Headers(
        "x-rapidapi-host: exercisedb.p.rapidapi.com",
        "x-rapidapi-key: $ExerciseDbApiKey"
    )
    @GET("bodyPart/{bodyPart}")
    suspend fun getExercisesByBodyPart(@Path("bodyPart") bodyPart: String): List<ResponseExercise>
}
