package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.azuretour.ui.tourTip.model.StepModel
import com.sample.azuretour.ui.tourTip.theme.TourtipTheme

@Composable
internal fun CardComponent(
    modifier: Modifier = Modifier,
    message: @Composable () -> Unit,
    onClose: (() -> Unit)?,
    onNext: () -> Unit,
    stepModel: StepModel?,
    isLayoutInverted: Boolean = false
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp//TourtipTheme.elevation.extra
        ),
        shape = RoundedCornerShape(size = 0.dp/*TourtipTheme.radius.large*/),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent//backgroundColor,
        )
    ) {

        Column(
            modifier = Modifier.padding(horizontal = 0.dp/*TourtipTheme.dimen.dp16*/)
        ) {
            if (isLayoutInverted) {
                // If inverted (Card is BELOW target), show Content TOP, Buttons BOTTOM
                ContentSection(message)
                Spacer(modifier = Modifier.height(16.dp))
                NavigationSection(onClose, onNext, stepModel)
            } else {
                // Default (Card is ABOVE target), show Buttons TOP, Content BOTTOM
                NavigationSection(onClose, onNext, stepModel)
                Spacer(modifier = Modifier.height(16.dp))
                ContentSection(message)
            }
        }
    }
}

@Composable
private fun NavigationSection(
    onClose: (() -> Unit)?,
    onNext: () -> Unit,
    stepModel: StepModel?
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
                    .background(Color.White.copy(alpha = 0.2f))
                    .clickable(onClick = onClick)
                    .padding(horizontal = 18.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Skip Instructions",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }

        val buttonText = if (stepModel?.currentStep == null) "Done" else "Next"

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .clickable { onNext() }
                .padding(horizontal = 18.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                buttonText,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 13.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
private fun ContentSection(message: @Composable () -> Unit) {
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
                LocalTextStyle provides centeredTextStyle,
                content = message
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