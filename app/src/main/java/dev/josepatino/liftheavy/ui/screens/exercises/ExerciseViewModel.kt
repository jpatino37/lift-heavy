package dev.josepatino.liftheavy.ui.screens.exercises

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.josepatino.liftheavy.repository.ExerciseDbRepository
import dev.josepatino.liftheavy.ui.screens.exercises.model.ResponseExercise
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

    private var _searchVal = MutableLiveData<String>()
    val searchVal: LiveData<String> get() = _searchVal

    fun onValueChange(searchQuery: String) {
        _searchVal.value = searchQuery
        if (searchQuery.isEmpty()) {
            _filteredList.value = _exercisesByBodyPart.value
        } else {
            _filteredList.value = _exercisesByBodyPart.value?.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    private var _exercisesByBodyPart = MutableLiveData<List<ResponseExercise>>()

    private var _filteredList = MutableLiveData<List<ResponseExercise>>()
    val filteredList: LiveData<List<ResponseExercise>> get() = _filteredList

    init {
        getExercisesByBodyPart()
    }

    private fun getExercisesByBodyPart(bodyPart: String = "back") = viewModelScope.launch {
        try {
            val exerciseList = exerciseDbRepository.getExercisesByBodyPart(bodyPart = bodyPart)
            _exercisesByBodyPart.value = exerciseList
            _filteredList.value = exerciseList
        } catch (e: Exception) {
            Log.d("vm", e.message.toString())
        }
    }

}