package com.sample.azuretour.ui.tourTip.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.sample.azuretour.ui.theme.AzureTourTheme
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalPlutoElevation
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalPlutoOpacity
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalPlutoRadius
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalPlutoSizing

@Composable
internal fun TourtipTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPlutoOpacity provides Opacity,
        LocalPlutoRadius provides Radius,
        LocalPlutoSizing provides Dimens,
        LocalPlutoElevation provides Elevation
    ) {
        AzureTourTheme {
            content()
        }
    }
}


internal object TourtipTheme {

    val opacity: Opacity
        @Composable
        get() = LocalPlutoOpacity.current

    val radius: Radius
        @Composable
        get() = LocalPlutoRadius.current

    val dimen: Dimens
        @Composable
        get() = LocalPlutoSizing.current

    val elevation: Elevation
        @Composable
        get() = LocalPlutoElevation.current
}
