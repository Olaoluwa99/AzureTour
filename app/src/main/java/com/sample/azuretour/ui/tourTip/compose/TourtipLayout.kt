package com.sample.azuretour.ui.tourTip.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.yihua.giftcard.ui.tourTip.compose.component.TourtipComponent
import com.sample.azuretour.ui.tourTip.model.TourtipAnimType
import com.sample.azuretour.ui.tourTip.model.TourtipController
import com.sample.azuretour.ui.tourTip.theme.defaults.TourtipDefaults

@Composable
fun TourtipLayout(
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onBack: (currentStep: Int) -> Unit = {},
    onNext: (currentStep: Int) -> Unit = {},
    onClose: ((currentStep: Int) -> Unit)? = null,
    onClickOut: ((currentStep: Int) -> Unit)? = null,
    animType: TourtipAnimType = TourtipAnimType.Bouncy,
    scrimColor: Color = TourtipDefaults.scrimColor,
    backgroundColor: Color = Color.White,
    content: @Composable ColumnScope.(controller: TourtipController) -> Unit
) {

    TourtipComponent(
        innerPadding = innerPadding,
        onClose = onClose,
        onBack = onBack,
        onNext = onNext,
        onClickOut = onClickOut,
        animType = animType,
        scrimColor = scrimColor,
        backgroundColor = backgroundColor,
        content = { viewModel->
            Column(
                modifier = modifier
            ) {
                content(viewModel)
            }
        }
    )
}
