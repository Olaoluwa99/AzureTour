package com.sample.azuretour.ui.tourTip.model

import androidx.compose.ui.unit.Dp
import com.sample.azuretour.ui.tourTip.theme.Dimens

internal data class TooltipPosition(
    val isCaretUp: Boolean,
    val caretMargin: Dp = Dimens.dp8,
    val caretWidth: Dp = Dimens.dp16,
    val caretHeight: Dp = Dimens.dp16
)
