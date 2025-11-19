package com.sample.azuretour.ui.tourTip.model

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp
import com.sample.azuretour.ui.tourTip.theme.Dimens

internal data class OverlayModel(
    val padding: Dp = Dimens.dp4,
    val outerRadius: Dp = Dimens.dp12,
    val targetBounds: Rect = Rect.Zero,
    val highlightType: HighlightType = HighlightType.Rounded
)
