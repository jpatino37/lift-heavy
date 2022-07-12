package dev.josepatino.liftheavy.ui.components

import android.graphics.Paint
import android.graphics.Path
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.josepatino.liftheavy.ui.PlateSize

@Composable
fun Plate(size: PlateSize) {
    Box() {
        when (size) {
            PlateSize.FortyFive -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(75.dp),
                color = Color.Blue
            ) {}
            PlateSize.ThirtyFive -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(65.dp),
                color = Color.Yellow
            ) {}
            PlateSize.TwentyFive -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(55.dp),
                color = Color.Green
            ) {}
            PlateSize.Fifteen -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(45.dp),
                color = Color.Black
            ) {}
            PlateSize.Ten -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(35.dp),
                color = Color.Black
            ) {}
            PlateSize.Five -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(25.dp),
                color = Color.Black
            ) {}
            PlateSize.TwoPointFive -> Surface(
                shape = CircleShape,
                modifier = Modifier.size(15.dp),
                color = Color.Black
            ) {}
        }
        Surface(
            shape = CircleShape, modifier = Modifier
                .size(8.dp)
                .align(Alignment.Center), color = Color.LightGray
        ) {}
        Surface(
            shape = CircleShape,
            modifier = Modifier
                .size(5.dp)
                .align(Alignment.Center),
            color = Color.DarkGray
        ) {}
    }
}

@Composable
@Preview(showBackground = true)
fun PlatePreview() {
    Column {
        Plate(PlateSize.FortyFive)
        Plate(PlateSize.ThirtyFive)
        Plate(PlateSize.TwentyFive)
        Plate(PlateSize.Fifteen)
        Plate(PlateSize.Ten)
        Plate(PlateSize.Five)
        Plate(PlateSize.TwoPointFive)
    }
}

//        // Creating a canvas with various attributes
//        Canvas(modifier = Modifier.size(80.dp)) {
//            drawIntoCanvas {
//                val textPadding = 25.dp.toPx()
//                val arcHeight = 110.dp.toPx()
//                val arcWidth = 80.dp.toPx()
//
//                // Path for curved text
//                val path = Path().apply {
//                    addArc(0f, textPadding, arcWidth, arcHeight, 180f, 180f)
//                }
//                it.nativeCanvas.drawTextOnPath(
//                    "Lift Heavy",
//                    path,
//                    0f,
//                    0f,
//                    Paint().apply {
//                        textSize = 15.sp.toPx()
//                        textAlign = Paint.Align.CENTER
//                    }
//                )
//            }
//        }