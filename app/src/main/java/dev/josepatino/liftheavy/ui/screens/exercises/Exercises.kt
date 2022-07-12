package dev.josepatino.liftheavy.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.josepatino.liftheavy.model.ResponseExercise
import dev.josepatino.liftheavy.model.ResponseExerciseList
import dev.josepatino.liftheavy.repository.ExerciseDbRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun Exercises(exercises: List<ResponseExercise>) {
    LazyColumn {
        items(exercises) {
            ListItem(it.name)
        }
    }
}

@Composable
fun ListItem(name: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(Icons.Filled.Build, contentDescription = "Localized description")
        Text(text = name)
    }
}