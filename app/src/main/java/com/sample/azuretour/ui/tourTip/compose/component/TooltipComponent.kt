package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.sample.azuretour.ui.tourTip.compose.extension.toDp
import com.sample.azuretour.ui.tourTip.compose.extension.toPx
import com.sample.azuretour.ui.tourTip.model.ShapeType
import com.sample.azuretour.ui.tourTip.model.StepModel
import com.sample.azuretour.ui.tourTip.model.TourtipAnimType
import com.sample.azuretour.ui.tourTip.theme.TourtipTheme

@Composable
internal fun TooltipComponent(
    targetBounds: Rect,
    message: @Composable () -> Unit,
    onClose: (() -> Unit)?,
    onNext: () -> Unit,
    stepModel: StepModel?,
    backgroundColor: Color,
    animType: TourtipAnimType
) {
    var cardSize by remember { mutableStateOf(Size.Zero) }

    // Configuration for positioning
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp.toPx()

    // Determine if target is in top or bottom half of screen
    val isTargetAtTop = targetBounds.center.y < (screenHeight / 2)

    // If target is at top, card shows BELOW (caret points up)
    // If target is at bottom, card shows ABOVE (caret points down)
    val isCaretUp = isTargetAtTop

    val caretMargin = 40.dp
    val caretWidth = 16.dp
    val caretHeight = 16.dp

    val initialCardYOffset = initialCardYOffset(
        targetBounds = targetBounds,
        cardSize = cardSize,
        caretHeight = caretHeight,
        caretMargin = caretMargin,
        isTargetAtTop = isTargetAtTop
    )

    val cardYOffset by animateDpAsState(
        targetValue = initialCardYOffset,
        animationSpec = animType.animOf(animType),
        label = animType.label
    )

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()

    // Caret always centered at bottom of card (horizontally centered on screen)
    val caretXOffset = (screenWidth / 2) - (caretWidth.toPx() / 2)

    // Calculate Caret Y based on position
    val caretYOffset = if (isTargetAtTop) {
        // Card is below, Caret is at top of card
        cardYOffset.toPx() - caretHeight.toPx()
    } else {
        // Card is above, Caret is at bottom of card
        cardYOffset.toPx() + cardSize.height
    }

    // Top center of target item
    val targetCenterX = targetBounds.left + (targetBounds.width / 2)
    val targetConnectionY = if (isTargetAtTop) targetBounds.bottom else targetBounds.top

    // Center of caret
    val caretCenterX = caretXOffset + (caretWidth.toPx() / 2)
    val caretCenterY = caretYOffset + (caretHeight.toPx() / 2)

    // Line goes from caret center to target connection point
    val lineStartX = caretCenterX
    val lineStartY = caretCenterY

    var mPaddingStart = 0.dp
    var mPaddingEnd = 0.dp
    var mConnectorStartX = 0f
    var mCaretOffsetX = 0f

    // Logic for shifting caret for specific steps (inherited from original)
    when (stepModel?.currentStep) {
        0,1,2,3,4 -> {
            mPaddingStart = 0.dp
            mPaddingEnd =  36.dp
            mConnectorStartX = lineStartX - 18.dp.toPx()
            mCaretOffsetX = caretXOffset - 18.dp.toPx()
        }
        else -> {
            mPaddingStart = 36.dp
            mPaddingEnd =  0.dp
            mConnectorStartX = lineStartX + 18.dp.toPx()
            mCaretOffsetX = caretXOffset + 18.dp.toPx()
        }
    }

    Box(modifier = Modifier.fillMaxWidth().padding(start = mPaddingStart, end = mPaddingEnd)) {
        CardComponent(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(x = 0, y = cardYOffset.roundToPx()) }
                .padding(horizontal = TourtipTheme.dimen.dp24)
                .onGloballyPositioned { coordinates ->
                    cardSize = coordinates.size.toSize()
                },
            message = message,
            onClose = onClose,
            onNext = onNext,
            stepModel = stepModel,
            isLayoutInverted = isTargetAtTop // Invert layout if target is at top (card is below)
        )
    }

    // Angular white line from caret center to target
    ConnectorLineComponent(
        startX = mConnectorStartX,
        startY = lineStartY,
        endX = targetCenterX,
        endY = targetConnectionY
    )

    CaretComponent(
        isCaretUp = isCaretUp,
        caretWidth = caretWidth,
        caretHeight = caretHeight,
        xOffset = mCaretOffsetX,
        yOffset = caretYOffset,
        targetBounds = targetBounds,
        color = backgroundColor,
        shapeType = ShapeType.Center
    )
}

@Composable
private fun initialCardYOffset(
    targetBounds: Rect,
    cardSize: Size,
    caretHeight: Dp,
    caretMargin: Dp,
    isTargetAtTop: Boolean
): Dp {
    return if (isTargetAtTop) {
        // Card below target
        (targetBounds.bottom + caretMargin.toPx() + caretHeight.toPx()).toDp()
    } else {
        // Card above target
        (targetBounds.top - cardSize.height - caretHeight.toPx() - caretMargin.toPx()).toDp()
    }
}