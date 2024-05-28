package com.yash.cardsstack

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
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
    Canvas(modifier = modifier.fillMaxSize()) {
        val squareSize = size / 2f
        val increasedWidth = squareSize.width * 1.3f
        val centeredLeft = (size.width - increasedWidth) / 2f  // Center the rectangle horizontally
        val centeredTop = (size.height - squareSize.height) / 2f // Center the rectangle vertically

        val blendedColor = lerp(Color.White, Color(0xFF773EDF), 0.5f)
        withTransform(transformBlock = {
            translate(top = centeredTop -20f, left = centeredLeft)
            rotate(degrees = -4f, pivot = Offset(centeredLeft + increasedWidth / 2, centeredTop + squareSize.height / 2))
        }){
            drawRoundRect(
                color = blendedColor, // Change to desired background color
                size = Size(increasedWidth*1.05f, squareSize.height*1.01f),
                cornerRadius = CornerRadius(40.dp.toPx(), 40.dp.toPx()),
            )
        }


        // Draw the foreground rectangle
        translate(top = centeredTop, left = centeredLeft) {
            drawRoundRect(
                color = Color(0xFF773EDF),
                size = Size(increasedWidth, squareSize.height),
                cornerRadius = CornerRadius(40.dp.toPx(), 40.dp.toPx()),
            )
        }
    }
}

@Preview
@Composable
private fun CardStackPreview() {
    CardStack()
}