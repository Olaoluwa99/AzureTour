package com.sample.azuretour.ui.tourTip.model

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

sealed class HighlightType {

    data object Rectangle : HighlightType()

    data object Rounded : HighlightType()

    data object Circle : HighlightType()

    class Custom(val draw: Path.(adjustedBounds: Rect) -> Unit) : HighlightType()
}
