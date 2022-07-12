package dev.josepatino.liftheavy.ui.screens

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.liftheavy.model.ResponseExerciseList
import dev.josepatino.liftheavy.repository.ExerciseDbRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val exerciseDbRepository: ExerciseDbRepository
) : ViewModel() {

    val bodyPartList = listOf(
        "back",
        "cardio",
        "chest",
        "lower arms",
        "lower legs",
        "neck",
        "shoulders",
        "upper arms",
        "upper legs",
        "waist"
    )

    private var _exercisesByBodyPart = MutableLiveData<ResponseExerciseList>()
    val exercisesByBodyPart: LiveData<ResponseExerciseList> get() = _exercisesByBodyPart

    init {
        getExercisesByBodyPart()
    }

    private fun getExercisesByBodyPart(bodyPart: String = "back") = viewModelScope.launch {
        try {
            exerciseDbRepository.getExercisesByBodyPart(bodyPart = bodyPart)
        } catch (e: Exception) {
            Log.d("vm", e.message.toString())
        }
    }

}