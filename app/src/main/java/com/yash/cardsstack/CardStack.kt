package com.yash.cardsstack

import android.annotation.SuppressLint
import android.os.Build
import android.text.format.Time
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yash.cardsstack.ui.theme.fontFamily
import kotlinx.coroutines.delay
import java.util.Timer
import java.util.concurrent.TimeUnit

@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CardStack(
    taskName: String,
    timerInMinutes:Int = 25,
    backgroundColor:Color = Color(0xFF773EDF),
    tiltAngle: Float = -10f,
    dampingRation:Float = 0.1f,
    stiffness:Float = 50f,
    modifier: Modifier = Modifier
) {
    // State to control the rotation angle
    var rotationAngle by remember { mutableFloatStateOf(0f) }

    // Animate the rotation angle to 10 degrees
    val animatedRotationAngle by animateFloatAsState(
        targetValue = rotationAngle,
        animationSpec = spring(
            dampingRatio = dampingRation, // Adjust damping ratio for bounce effect
            stiffness = stiffness // Adjust stiffness for bounce effect
        ), label = "HI"
        // Adjust duration as needed
    )
    val secondRectColor = backgroundColor
    val blendedColor = lerp(
        Color.White,
        secondRectColor,
        0.5f
    )

    // State to control timer
    var timeLeft by remember {
        mutableIntStateOf(timerInMinutes * 60) // 25 minutes in seconds
    }
    var isRunning by remember {
        mutableStateOf(false)
    }
    // Trigger the animation when the composable enters the composition
    LaunchedEffect(Unit) {
        rotationAngle = tiltAngle
    }

    // Timer Logic
    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                delay(1000L)
                timeLeft--
            }
            isRunning = false
        }
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val squareSize = size / 2f
            val increasedWidth = squareSize.width * 1.3f
            val centeredLeft =
                (size.width - increasedWidth) / 2f  // Center the rectangle horizontally
            val centeredTop =
                (size.height - squareSize.height) / 2f // Center the rectangle vertically

            // Blend white with the second rectangle's color

            // Draw the background rectangle first, with animated rotation
            rotate(
                degrees = animatedRotationAngle,
                pivot = Offset(
                    centeredLeft + increasedWidth / 2,
                    centeredTop + squareSize.height / 2
                )
            ) {
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

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                onClick = { isRunning = !isRunning },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0x37FFFFFF)),
            ) {
                if (isRunning) {
                    Icon(
                        imageVector = Icons.Default.Pause, contentDescription = "Pause",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.PlayArrow, contentDescription = "Pause",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = taskName, fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = fontFamily
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview
@Composable
private fun CardStackPreview() {
    CardStack(taskName = "Research", timerInMinutes = 25, backgroundColor = Color(0xFF773EDF))
}