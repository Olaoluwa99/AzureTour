package com.sample.azuretour.ui.tourTip.model

import androidx.annotation.IntRange
import androidx.compose.runtime.Composable

data class TooltipModel(
    @IntRange(from = 0)
    val index: Int,
    val title: @Composable (() -> Unit)? = null,
    val message: @Composable () -> Unit,
    val extraMessage: @Composable () -> Unit,
    val action: @Composable ((controller: TourtipController) -> Unit)? = null,
    val highlightType: HighlightType,
)
