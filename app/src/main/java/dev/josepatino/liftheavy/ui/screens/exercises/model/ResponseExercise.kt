package dev.josepatino.liftheavy.ui.screens.exercises.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseExercise(
    @Json(name = "bodyPart")
    val bodyPart: String,

    val equipment: String,

    @Json(name = "gifUrl")
    val gifUrl: String,
    val id: String,
    val name: String,
    val target: String
)
