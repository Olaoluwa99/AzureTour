package com.sample.azuretour.ui.tourTip.theme.defaults

import androidx.compose.runtime.staticCompositionLocalOf
import com.sample.azuretour.ui.tourTip.model.BoundsRegistry
import com.sample.azuretour.ui.tourTip.theme.Dimens
import com.sample.azuretour.ui.tourTip.theme.Elevation
import com.sample.azuretour.ui.tourTip.theme.Opacity
import com.sample.azuretour.ui.tourTip.theme.Radius

internal val LocalBoundsRegistry = staticCompositionLocalOf<BoundsRegistry> {
    error("No BoundsRegistry provided")
}

internal val LocalPlutoOpacity = staticCompositionLocalOf {
    Opacity
}

internal val LocalPlutoRadius = staticCompositionLocalOf {
    Radius
}

internal val LocalPlutoSizing = staticCompositionLocalOf {
    Dimens
}

internal val LocalPlutoElevation = staticCompositionLocalOf {
    Elevation
}
