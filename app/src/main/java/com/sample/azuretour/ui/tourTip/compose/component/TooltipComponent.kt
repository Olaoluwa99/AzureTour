package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.animation.core.animateDpAsState
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
import com.sample.azuretour.ui.tourTip.model.TooltipPosition
import com.sample.azuretour.ui.tourTip.model.TourtipAnimType
import com.sample.azuretour.ui.tourTip.theme.TourtipTheme

@Composable
internal fun TooltipComponent(
    targetBounds: Rect,
    message: @Composable () -> Unit,
    action: @Composable (() -> Unit)?,
    onClose: (() -> Unit)?,
    onNext: () -> Unit,
    shouldShowNext: Boolean,
    shouldShowBack: Boolean,
    shouldShowSkip: Boolean,
    stepModel: StepModel?,
    backgroundColor: Color,
    animType: TourtipAnimType
) {
    var cardSize by remember { mutableStateOf(Size.Zero) }

    // Caret is always centered
    val caretMargin = 40.dp // Increased vertical spacing between tourtip and item
    val caretWidth = 16.dp
    val caretHeight = 16.dp

    // Caret and card always above the target
    val isCaretUp = true

    val initialCardYOffset = initialCardYOffset(
        targetBounds = targetBounds,
        cardSize = cardSize,
        caretHeight = caretHeight,
        caretMargin = caretMargin,
        isCaretUp = isCaretUp
    )

    val cardYOffset by animateDpAsState(
        targetValue = initialCardYOffset,
        animationSpec = animType.animOf(animType),
        label = animType.label
    )

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp.toPx()

    // Caret always centered at bottom of card (horizontally centered on screen)
    val caretXOffset = (screenWidth / 2) - (caretWidth.toPx() / 2)
    val caretYOffset = cardYOffset.toPx() + cardSize.height

    // Top center of target item
    val targetTopX = targetBounds.left + (targetBounds.width / 2)
    val targetTopY = targetBounds.top

    // Center of caret
    val caretCenterX = caretXOffset + (caretWidth.toPx() / 2)
    val caretCenterY = caretYOffset + (caretHeight.toPx() / 2)

    // Line goes from caret center to target top
    val lineStartX = caretCenterX
    val lineStartY = caretCenterY

    CardComponent(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(x = 0, y = cardYOffset.roundToPx()) }
            .padding(horizontal = TourtipTheme.dimen.dp24)
            .onGloballyPositioned { coordinates ->
                cardSize = coordinates.size.toSize()
            },
        message = message,
        action = action,
        onClose = onClose,
        onNext = onNext,
        stepModel = stepModel,
        backgroundColor = backgroundColor,
        shouldShowNext = shouldShowNext,
        shouldShowBack = shouldShowBack,
        shouldShowSkip = shouldShowSkip
    )

    // Angular white line from caret center to target top
    ConnectorLineComponent(
        startX = lineStartX,
        startY = lineStartY,
        endX = targetTopX,
        endY = targetTopY
    )

    CaretComponent(
        isCaretUp = false, // Caret points downward
        caretWidth = caretWidth,
        caretHeight = caretHeight,
        xOffset = caretXOffset,
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
    isCaretUp: Boolean
): Dp {
    // Card is always above target
    val initialCardYOffset = targetBounds.top - cardSize.height - caretHeight.toPx() - caretMargin.toPx()
    return initialCardYOffset.toDp()
}

/*@Composable
internal fun TooltipComponent(
    targetBounds: Rect,
    message: @Composable () -> Unit,
    action: @Composable (() -> Unit)?,
    onClose: (() -> Unit)?,
    onNext: () -> Unit,
    shouldShowNext: Boolean,
    shouldShowBack: Boolean,
    shouldShowSkip: Boolean,
    stepModel: StepModel?,
    backgroundColor: Color,
    animType: TourtipAnimType
) {
    var cardSize by remember { mutableStateOf(Size.Zero) }
    val (isCaretUp, caretMargin, caretWidth, caretHeight) = calculateAlignment(targetBounds)

    val initialCardYOffset = initialCardYOffset(
        targetBounds = targetBounds,
        cardSize = cardSize,
        caretHeight = caretHeight,
        caretMargin = caretMargin,
        isCaretUp = isCaretUp
    )

    val cardYOffset by animateDpAsState(
        targetValue = initialCardYOffset,
        animationSpec = animType.animOf(animType),
        label = animType.label
    )

    val (caretXOffset, caretYOffset, shapeType) = calculateCaretPosition(
        targetBounds = targetBounds,
        cardSize = cardSize,
        cardYOffset = cardYOffset,
        caretHeight = caretHeight,
        caretWidth = caretWidth,
        isCaretUp = isCaretUp
    )

    CardComponent(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(x = 0, y = cardYOffset.roundToPx()) }
            .padding(horizontal = TourtipTheme.dimen.dp24)
            .onGloballyPositioned { coordinates ->
                cardSize = coordinates.size.toSize()
            },
        message = message,
        action = action,
        onClose = onClose,
        onNext = onNext,
        stepModel = stepModel,
        backgroundColor = backgroundColor,
        shouldShowNext = shouldShowNext,
        shouldShowBack = shouldShowBack,
        shouldShowSkip = shouldShowSkip
    )
    CaretComponent(
        isCaretUp = isCaretUp,
        caretWidth = caretWidth,
        caretHeight = caretHeight,
        xOffset = caretXOffset,
        yOffset = caretYOffset,
        targetBounds = targetBounds,
        color = backgroundColor,
        shapeType = shapeType
    )
}*/

@Composable
private fun calculateAlignment(targetBounds: Rect): TooltipPosition {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp.toPx()
    val isCaretUp = targetBounds.top <= screenHeight / 2

    return TooltipPosition(isCaretUp = isCaretUp)
}

/*@Composable
fun initialCardYOffset(
    targetBounds: Rect,
    cardSize: Size,
    caretHeight: Dp,
    caretMargin: Dp,
    isCaretUp: Boolean
): Dp {
    val initialCardYOffset = if (isCaretUp) {
        targetBounds.bottom + caretHeight.toPx() + caretMargin.toPx()
    } else targetBounds.top - cardSize.height - caretHeight.toPx() - caretMargin.toPx()

    return initialCardYOffset.toDp()
}*/

@Composable
private fun calculateCaretPosition(
    targetBounds: Rect,
    cardSize: Size,
    cardYOffset: Dp,
    caretHeight: Dp,
    caretWidth: Dp,
    isCaretUp: Boolean
): Triple<Float, Float, ShapeType> {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.toPx()

    val xOffset = (targetBounds.left + targetBounds.width / 2) - (caretWidth.toPx() / 2)
    val caretYOffset = if (isCaretUp) {
        cardYOffset.toPx() - caretHeight.toPx()
    } else cardYOffset.toPx() + cardSize.height

    val shapeType = calculateShapeType(xOffset, screenWidth)

    val caretXOffset = when (shapeType) {
        ShapeType.Center -> xOffset
        ShapeType.LeftBounds -> (xOffset + caretWidth.toPx())
            .coerceAtLeast(minimumValue = 0f)

        ShapeType.RightBounds -> (xOffset - caretWidth.toPx())
            .coerceAtMost(maximumValue = screenWidth - caretWidth.toPx())
    }

    return Triple(caretXOffset, caretYOffset, shapeType)
}

@Composable
private fun calculateShapeType(
    xOffset: Float,
    screenWidth: Float
): ShapeType {
    val leftBoundaryEnd = screenWidth * 0.15
    val rightBoundaryStart = screenWidth * 0.85

    return when {
        xOffset < leftBoundaryEnd -> ShapeType.LeftBounds
        xOffset > rightBoundaryStart -> ShapeType.RightBounds
        else -> ShapeType.Center
    }
}
