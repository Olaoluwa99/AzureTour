package com.sample.azuretour.ui.tourTip.compose.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sample.azuretour.ui.tourTip.model.TooltipModel
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalBoundsRegistry

fun Modifier.tooltipAnchor(model: () -> TooltipModel): Modifier = composed {
    val registry = LocalBoundsRegistry.current
    val boundsUpdated = remember(model().index) {
        { coordinates: LayoutCoordinates ->
            val bounds = coordinates.boundsInRoot()
            registry.updateBounds(model = model(), bounds = bounds)
        }
    }
    onGloballyPositioned(boundsUpdated)
}

internal fun Rect.isTargetZero(): Boolean {
    return this == Rect.Zero
}

@Composable
internal fun Float.toDp(): Dp {
    val density = LocalDensity.current.density
    return (this / density).dp
}

@Composable
internal fun Dp.toPx(): Float {
    val density = LocalDensity.current.density
    return value * density
}
