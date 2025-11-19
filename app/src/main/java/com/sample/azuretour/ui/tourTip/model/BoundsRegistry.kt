package com.sample.azuretour.ui.tourTip.model

import androidx.compose.ui.geometry.Rect

internal interface BoundsRegistry {
    fun updateBounds(model: TooltipModel, bounds: Rect)
}
