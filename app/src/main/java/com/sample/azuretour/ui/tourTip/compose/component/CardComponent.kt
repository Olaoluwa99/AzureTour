package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.azuretour.ui.tourTip.model.StepModel
import com.sample.azuretour.ui.tourTip.theme.TourtipTheme

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    message: @Composable () -> Unit,
    action: @Composable (() -> Unit)?,
    onClose: (() -> Unit)?,
    onNext: () -> Unit,
    stepModel: StepModel?,
    shouldShowNext: Boolean,
    shouldShowBack: Boolean,
    shouldShowSkip: Boolean,
    backgroundColor: Color
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp//TourtipTheme.elevation.extra
        ),
        shape = RoundedCornerShape(size = TourtipTheme.radius.large),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent//backgroundColor,
        )
    ) {

        Column(
            modifier = Modifier.padding(horizontal = TourtipTheme.dimen.dp16)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                onClose?.let { onClick ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                            .background(Color.Transparent)
                            .clickable (onClick = onClick)
                            .padding(horizontal = 18.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Skip Instructions", color = Color.White, fontSize = 13.sp)
                    }
                }

                if (stepModel?.currentStep == null){
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .clickable { onNext() }
                            .padding(horizontal = 18.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(" Done ", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    }
                }else{
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .clickable { onNext() }
                            .padding(horizontal = 18.dp, vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text("Next Step", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 12.dp, horizontal = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val centeredTextStyle = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    )
                    CompositionLocalProvider(
                        // 3. Provide the modified style
                        LocalTextStyle provides centeredTextStyle,
                        content = message
                    )
                    /*CompositionLocalProvider(
                        LocalTextStyle provides MaterialTheme.typography.bodySmall,
                        content = message
                    )*/
                }
            }

            /*StepComponent(
                stepModel = stepModel ?: StepModel(0, 3),
                onBack = onBack,
                onNext = onNext,
                onSkip = {
                    onClose?.invoke()
                },
                shouldShowNext = shouldShowNext,
                shouldShowSkip = shouldShowSkip,
                shouldShowBack = shouldShowBack
            )*/
        }
    }
}

@Stable
@Composable
private fun Modifier.textVerticalPadding(
    subheadExists: Boolean,
    actionExists: Boolean
): Modifier {
    return if (!subheadExists && !actionExists) {
        this.padding(vertical = TourtipTheme.dimen.dp4)
    } else {
        this
            .paddingFromBaseline(top = TourtipTheme.dimen.dp24)
            .padding(bottom = TourtipTheme.dimen.dp16)
    }
}