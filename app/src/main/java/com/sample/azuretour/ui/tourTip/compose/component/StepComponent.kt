package com.sample.azuretour.ui.tourTip.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.azuretour.ui.tourTip.model.StepModel
import com.sample.azuretour.ui.tourTip.theme.TourtipTheme

@Composable
internal fun StepComponent(
    stepModel: StepModel,
    shouldShowNext: Boolean,
    shouldShowBack: Boolean,
    shouldShowSkip: Boolean,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onSkip: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        if (shouldShowSkip){
            Text(
                text = "Skip",
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable{ onSkip() }
            )
        }

        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ){
            if(shouldShowBack){
                OutlinedButton(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
                    onClick = { onBack() },
                    modifier = Modifier.height(30.dp)
                ) {
                    Text("  Back  ")
                }
            }
            Spacer(modifier = Modifier.size(TourtipTheme.dimen.dp12))
            if(shouldShowNext){
                Button(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
                    onClick = { onNext() },
                    modifier = Modifier.height(30.dp)
                ) {
                    Text("  Next  ", fontSize = 14.sp)
                }
            }else{
                Button(
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 2.dp),
                    onClick = { onNext() },
                    modifier = Modifier.height(30.dp)
                ) {
                    Text("  Got it  ", fontSize = 14.sp)
                }
            }
        }
    }
}

private fun StepModel.isBackButtonEnabled(): Boolean {
    return currentStep.minimumValue() > 1
}

private fun StepModel.isNextButtonEnabled(): Boolean {
    return currentStep.minimumValue() < totalSteps.minimumValue()
}

private fun Int.minimumValue(): Int {
    return coerceAtLeast(minimumValue = 1)
}
