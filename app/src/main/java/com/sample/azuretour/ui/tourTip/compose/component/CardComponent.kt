package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sample.azuretour.ui.tourTip.model.StepModel
import com.sample.azuretour.ui.tourTip.theme.TourtipTheme

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    title: @Composable (() -> Unit)?,
    message: @Composable () -> Unit,
    extraMessage: @Composable (() -> Unit)?,
    action: @Composable (() -> Unit)?,
    onClose: (() -> Unit)?,
    onBack: () -> Unit,
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
            defaultElevation = TourtipTheme.elevation.extra
        ),
        shape = RoundedCornerShape(size = TourtipTheme.radius.large),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        )
    ) {

        Column(
            modifier = Modifier.padding(TourtipTheme.dimen.dp16)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.End
            ) {

                title?.let { content ->
                    Box(
                        modifier = Modifier.weight(weight = 1f),
                    ) {
                        CompositionLocalProvider(
                            LocalTextStyle provides MaterialTheme.typography.headlineLarge.copy(color = Color.Black),
                            content = content
                        )
                    }
                }
                onClose?.let { onClick ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                            .padding(4.dp)
                    ){
                        Icon(
                            modifier = Modifier
                                //.clip(CircleShape)
                                .size(16.dp)
                                .clickable(onClick = onClick),
                            imageVector = Icons.Default.Close,
                            tint = MaterialTheme.colorScheme.onBackground,
                            contentDescription = "Close",
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box {
                Column {
                    CompositionLocalProvider(
//                        LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant,
                        LocalTextStyle provides MaterialTheme.typography.bodyMedium,
                        content = message
                    )


                    extraMessage?.let { extra->
                        Spacer(modifier = Modifier.height(8.dp))
                        CompositionLocalProvider(
                            LocalContentColor provides Color(0xFF059C66),
                            LocalTextStyle provides MaterialTheme.typography.bodyMedium,
                            content = extra
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            StepComponent(
                stepModel = stepModel ?: StepModel(0, 3),
                onBack = onBack,
                onNext = onNext,
                onSkip = {
                    onClose?.invoke()
                },
                shouldShowNext = shouldShowNext,
                shouldShowSkip = shouldShowSkip,
                shouldShowBack = shouldShowBack
            )
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