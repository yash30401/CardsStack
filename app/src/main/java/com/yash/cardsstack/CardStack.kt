package com.yash.cardsstack

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardStack(modifier: Modifier = Modifier) {
    // State to control the rotation angle
    var rotationAngle by remember { mutableStateOf(0f) }

    // Animate the rotation angle to 10 degrees
    val animatedRotationAngle by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = spring(
            dampingRatio = 0.1f, // Adjust damping ratio for bounce effect
            stiffness = 50f // Adjust stiffness for bounce effect
        )
        // Adjust duration as needed
    )

    // Trigger the animation when the composable enters the composition
    LaunchedEffect(Unit) {
        rotationAngle = -10f
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val squareSize = size / 2f
        val increasedWidth = squareSize.width * 1.3f
        val centeredLeft = (size.width - increasedWidth) / 2f  // Center the rectangle horizontally
        val centeredTop = (size.height - squareSize.height) / 2f // Center the rectangle vertically

        val secondRectColor = Color(0xFF773EDF)
        val blendedColor = lerp(Color.White, secondRectColor, 0.5f)  // Blend white with the second rectangle's color

        // Draw the background rectangle first, with animated rotation
        rotate(degrees = animatedRotationAngle, pivot = Offset(centeredLeft + increasedWidth / 2, centeredTop + squareSize.height / 2)) {
            drawRoundRect(
                color = blendedColor, // Use the blended color
                size = Size(increasedWidth, squareSize.height),
                topLeft = Offset(centeredLeft, centeredTop),
                cornerRadius = CornerRadius(40.dp.toPx(), 40.dp.toPx())
            )
        }

        // Draw the foreground rectangle
        drawRoundRect(
            color = secondRectColor,
            size = Size(increasedWidth, squareSize.height),
            topLeft = Offset(centeredLeft, centeredTop),
            cornerRadius = CornerRadius(40.dp.toPx(), 40.dp.toPx()),
        )
    }
}

@Preview
@Composable
private fun CardStackPreview() {
    CardStack()
}