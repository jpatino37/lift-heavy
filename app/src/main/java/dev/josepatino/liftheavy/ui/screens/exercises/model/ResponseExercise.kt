package dev.josepatino.liftheavy.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseExercise(
    val bodyPart: String,
    val equipment: String,
    val gifUrl: String,
    val id: String,
    val name: String,
    val target: String
)
