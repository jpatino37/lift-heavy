package dev.josepatino.liftheavy.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import dev.josepatino.liftheavy.AddSetSheetContent
import dev.josepatino.liftheavy.R
import dev.josepatino.liftheavy.ui.theme.Primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun ActiveWorkout() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            AddSetSheetContent(scope = scope, scaffoldState = scaffoldState)
        },
        sheetElevation = 10.dp,
        sheetShape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp
    )
    { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val pagerState = rememberPagerState()
            val workoutList = listOf("Back Squat", "Barbell Lunges", "Dumbbell Curl")

            // Display 10 items
            HorizontalPager(
                count = workoutList.size,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(horizontal = 5.dp),
                modifier = Modifier
                    //.weight(1f)
                    .fillMaxWidth(),
            ) {
                WorkoutCard(
                    onAddSet = { scope.launch { scaffoldState.bottomSheetState.expand() } },
                    Pair(workoutList[pagerState.currentPage], pagerState.currentPage)
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp),
                activeColor = Primary
            )

//        ActionsRow(
//            pagerState = pagerState,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )

            Button(onClick = { /*TODO*/ }) {
                Text("End Workout")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutCard(onAddSet: () -> Unit, exerciseAndKey: Pair<String, Int>) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier.padding(15.dp), verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row() {
                Text(exerciseAndKey.first, fontSize = 24.sp)
                if (false) {
                    Icon(
                        Icons.Filled.Done,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.backsquat),
                contentDescription = "",
                modifier = Modifier.size(150.dp),
                contentScale = ContentScale.Crop
            )
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 128.dp),
            ) {
                items(6) {
                    ExerciseCard()
                }
                item {
                    CircularButton(onClick = { onAddSet() })
                }
            }
        }
    }
}


@Composable
fun CircularButton(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }, modifier = Modifier.clip(shape = CircleShape)) {
        Icon(
            Icons.Filled.Add,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
    }
}

@Composable
fun ExerciseCard() {
    Card(shape = RoundedCornerShape(10.dp), elevation = 5.dp, modifier = Modifier.padding(5.dp)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Weight: 235 lbs")
            Text("Reps: 6")
            Text("Difficulty: Hard")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoutinePreview() {
    ActiveWorkout()
}