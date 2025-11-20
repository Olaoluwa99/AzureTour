package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
internal fun ConnectorLineComponent(
    startX: Float,
    startY: Float,
    endX: Float,
    endY: Float
) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        drawLine(
            color = Color.White,
            start = Offset(startX, startY),
            end = Offset(endX, endY),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}