package com.yihua.giftcard.ui.tourTip.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.azuretour.ui.tourTip.compose.component.FocusOverlayComponent
import com.sample.azuretour.ui.tourTip.compose.component.TooltipComponent
import com.sample.azuretour.ui.tourTip.model.TourtipAnimType
import com.sample.azuretour.ui.tourTip.theme.defaults.LocalBoundsRegistry
import com.sample.azuretour.ui.tourTip.viewmodel.TourtipViewModel

@Composable
internal fun TourtipComponent(
    innerPadding: PaddingValues,
    onBack: (currentStep: Int) -> Unit,
    onNext: (currentStep: Int) -> Unit,
    onClose: ((currentStep: Int) -> Unit)?,
    onClickOut: ((currentStep: Int) -> Unit)?,
    scrimColor: Color,
    backgroundColor: Color,
    animType: TourtipAnimType,
    content: @Composable (TourtipViewModel) -> Unit
) {
    val viewModel: TourtipViewModel = viewModel()
    val state by viewModel.state.collectAsState()

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
                    onNext = {
                        onNext(state.currentStep);
                        viewModel.onNext()
                    },
                    message = state.tooltipModels[state.currentStep]?.message ?: {},
                    targetBounds = state.overlayModel.targetBounds,
                    backgroundColor = backgroundColor,
                )
                if (state.currentStep != 0){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = innerPadding.calculateTopPadding(), horizontal = 16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.White)
                                .clickable { onBack(state.currentStep); viewModel.onBack() }
                                .padding(horizontal = 18.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text("Previous", color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.W600)
                        }
                    }
                }
            }
        }
    }
}
