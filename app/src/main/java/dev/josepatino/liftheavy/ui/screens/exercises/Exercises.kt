package dev.josepatino.liftheavy.ui.screens.exercises

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.skydoves.landscapist.glide.GlideImage
import dev.josepatino.liftheavy.extensions.capitalizeEachWord
import dev.josepatino.liftheavy.extensions.convertToHttps
import dev.josepatino.liftheavy.ui.screens.exercises.model.ResponseExercise
import dev.josepatino.liftheavy.ui.screens.routines.model.Exercise
import dev.josepatino.liftheavy.ui.theme.Primary
import dev.josepatino.liftheavy.ui.theme.PrimaryLight

@Composable
fun Exercises(
    exercises: List<ResponseExercise>,
    searchVal: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            TextField(
                placeholder = {
                    Text(text = "Search for an exercise")
                },
                value = searchVal,
                onValueChange = {
                    onValueChange(it)
                },
                modifier = Modifier
                    .fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = { onValueChange("") },
                        enabled = searchVal.isNotEmpty()
                    ) {
                        Icon(Icons.Default.Clear, "clear button")
                    }
                }
            )
        }
        LazyColumn {
            items(exercises) {
                ExerciseItem(it)
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun ExercisesPreview() {
    Exercises(exercises = emptyList(), searchVal = "", onValueChange = {})
}

@Composable
fun ExerciseItem(exercise: ResponseExercise) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()

    Surface(elevation = 5.dp, modifier = Modifier.clickable {
        /*TODO: Add on click*/
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                border = BorderStroke(3.dp, SolidColor(PrimaryLight)),
                modifier = Modifier
                    .size(80.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = exercise.gifUrl.convertToHttps(),
                        imageLoader = imageLoader,
                        contentScale = ContentScale.Crop
                    ),
                    contentDescription = "exercise gif",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            Spacer(modifier = Modifier.size(5.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = exercise.name.capitalizeEachWord(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "Equipment Needed: ")
                    Text(text = exercise.equipment.capitalizeEachWord())
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExerciseItemPreview() {
    val exercise = ResponseExercise(
        bodyPart = "back",
        equipment = "lat pulldown machine",
        gifUrl = "url",
        id = "000890",
        name = "lat pulldown",
        target = "lats"
    )
    ExerciseItem(exercise = exercise)
}