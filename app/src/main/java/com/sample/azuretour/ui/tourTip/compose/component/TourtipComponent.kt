package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.azuretour.ui.tourTip.model.TourtipAnimType
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalBoundsRegistry
import com.sample.azuretour.ui.tourTip.viewmodel.TourtipViewModel

@Composable
internal fun TourtipComponent(
    isFinalPage: Boolean,
    onBack: (currentStep: Int) -> Unit,
    onNext: (currentStep: Int) -> Unit,
    onClose: ((currentStep: Int) -> Unit)?,
    onClickOut: ((currentStep: Int) -> Unit)?,
    scrimColor: Color,
    backgroundColor: Color,
    animType: TourtipAnimType,
    content: @Composable (TourtipViewModel) -> Unit,
    shouldShowNext: Boolean,
    shouldShowBack: Boolean,
    shouldShowSkip: Boolean
) {
    val viewModel: TourtipViewModel = viewModel()
    val state by viewModel.state.collectAsState()
    var isFinalItem by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadSteps()
    }

    CompositionLocalProvider(LocalBoundsRegistry provides viewModel) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            content(viewModel)
            if (state.isVisible) {
                FocusOverlayComponent(
                    scrimColor = scrimColor,
                    overlayModel = state.overlayModel,
                    onClickOut = { onClickOut?.let { it(state.currentStep); viewModel.onEnd() } }
                )
                TooltipComponent(
                    animType = animType,
                    stepModel = state.stepModel,
                    onClose = onClose?.let { { it(state.currentStep); viewModel.onEnd() } },
                    onBack = { onBack(state.currentStep); viewModel.onBack() },
                    onNext = {
                        onNext(state.currentStep);
                        viewModel.onNext()
                    },
                    title = state.tooltipModels[state.currentStep]?.title,
                    message = state.tooltipModels[state.currentStep]?.message ?: {},
                    extraMessage = state.tooltipModels[state.currentStep]?.extraMessage ?: {},
                    targetBounds = state.overlayModel.targetBounds,
                    backgroundColor = backgroundColor,
                    action = state.tooltipModels[state.currentStep]?.action?.let { action ->
                        { action.invoke(viewModel) }
                    },
                    shouldShowNext = shouldShowNext && !isFinalItem,
                    shouldShowBack = shouldShowBack && !isFinalItem,
                    shouldShowSkip = shouldShowSkip && !isFinalItem
                )
            }
        }
    }
}
