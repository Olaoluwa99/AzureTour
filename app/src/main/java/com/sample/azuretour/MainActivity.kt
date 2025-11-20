package com.sample.azuretour

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.azuretour.ui.theme.AzureTourTheme
import com.sample.azuretour.ui.tourTip.component.AnimationSelector
import com.sample.azuretour.ui.tourTip.component.BottomMenu
import com.sample.azuretour.ui.tourTip.component.IconLauncher
import com.sample.azuretour.ui.tourTip.component.tooltipAppName
import com.sample.azuretour.ui.tourTip.component.tooltipIconLauncher
import com.sample.azuretour.ui.tourTip.component.tooltipRadioGroupAnim
import com.sample.azuretour.ui.tourTip.component.tooltipStartTourtip
import com.sample.azuretour.ui.tourTip.component.tooltipTitle
import com.sample.azuretour.ui.tourTip.compose.TourtipLayout
import com.sample.azuretour.ui.tourTip.compose.extension.tooltipAnchor
import com.sample.azuretour.ui.tourTip.model.HighlightType
import com.sample.azuretour.ui.tourTip.model.TooltipModel
import com.sample.azuretour.ui.tourTip.model.TourtipAnimType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AzureTourTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
//                        modifier = Modifier.padding(innerPadding)
                    ){
                        TourtipSampleScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun TourtipSampleScreen() {
    var mCurrentStep by remember { mutableIntStateOf(0) }
    var animType by remember { mutableStateOf(TourtipAnimType.Bouncy) }

    TourtipLayout(
        modifier = Modifier.fillMaxSize(),
        animType = animType,
        onClose = { currentStep ->
            mCurrentStep = currentStep
            // Handle close for event tracking $currentStep
        },
        onBack = { currentStep ->
            mCurrentStep = (currentStep - 1).coerceAtLeast(0)
//            mCurrentStep = currentStep
            // Handle back for event tracking $currentStep
        },
        onNext = { currentStep ->
            mCurrentStep = currentStep + 1
//            mCurrentStep = currentStep
            // Handle next for event tracking $currentStep
        },
        isFinalPage = true,
        shouldShowNext = true,
        shouldShowBack = true,
        shouldShowSkip = true,
        onClickOut = {/**/}
    ) { controller ->

        AzureTourTheme {
            Scaffold(
                bottomBar = {
                    key (mCurrentStep){
                        BottomNavBar(
                            isSelected = when (mCurrentStep){
                                4 -> 2
                                5 -> 3
                                6 -> 4
                                else -> 1
                            }
                        )
                    }
                },
            ) { internalInnerPadding ->

                Column (
                    modifier = Modifier
                        .padding(top = internalInnerPadding.calculateTopPadding() + 20.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()

                ) {
                    /*TourtipSampleContent(
                        animType = animType,
                        onAnimChecked = { animType = it },
                        onStartClicked = {
                            controller.startTourtip()
                        }
                    )*/

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "General")
                    }
                    Spacer(Modifier.size(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()/*, horizontalArrangement = Arrangement.SpaceEvenly*/) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                .tourStep(tourSteps[1], HighlightType.Rounded)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "Sell Card")
                        }
                        Spacer(Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                .tourStep(tourSteps[2], HighlightType.Rounded)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "Bill payment")
                        }
                        Spacer(Modifier.width(12.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                                .tourStep(tourSteps[3], HighlightType.Rounded)
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = "Refer & Earn")
                        }
                    }


                    Spacer(Modifier.size(32.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .tooltipAnchor { tooltipStartTourtip() }
                            .size(52.dp),
                        shape = RoundedCornerShape(8.dp),
                        onClick = {
                            controller.startTourtip()
                        }
                    ) {
                        Text(text = "Start")
                    }
                }
            }
        }
    }
}

@Composable
fun ColumnScope.TourtipSampleContent(
    animType: TourtipAnimType,
    onAnimChecked: (TourtipAnimType) -> Unit,
    onStartClicked: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .weight(1f)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .tooltipAnchor { tooltipTitle() },
            text = "Let's start the Tourtip Sample!",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(Modifier.size(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconLauncher(
                modifier = Modifier
                    .size(50.dp)
                    .tooltipAnchor { tooltipIconLauncher(index = 1) },
            )
            IconLauncher(
                modifier = Modifier
                    .size(40.dp)
                    .tooltipAnchor { tooltipIconLauncher(index = 2) },
            )
            IconLauncher(
                modifier = Modifier
                    .size(30.dp)
                    .tooltipAnchor { tooltipIconLauncher(index = 3) },
            )
            Text(
                modifier = Modifier.tooltipAnchor {
                    tooltipAppName()
                },
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Spacer(Modifier.size(32.dp))
        Row(
            modifier = Modifier.tooltipAnchor {
                tooltipRadioGroupAnim()
            }
        ) {

            AnimationSelector(
                currentAnimType = animType,
                onAnimationSelected = onAnimChecked
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .tooltipAnchor { tooltipStartTourtip() }
                .size(52.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onStartClicked
        ) {
            Text(text = "Start")
        }
    }
    BottomMenu()
}



@Composable
fun BottomNavBar(
    isSelected: Int
) {
    val showDialog = remember { mutableStateOf(false) }
    var currentRoute by remember { mutableIntStateOf(1) }

    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Column {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.surfaceContainer
            )
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(vertical = 8.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BottomBarItem(
                    id = 1,
                    image = Icons.Default.Home,
                    title = "Home",
                    isSelected = isSelected == 1
                )
                BottomBarItem(
                    id = 2,
                    image = Icons.Default.Favorite,
                    title = "Order",
                    isSelected = isSelected == 2
                )
                BottomBarItem(
                    id = 3,
                    image = Icons.Default.Warning,
                    title = "Wallet",
                    isSelected = isSelected == 3
                )
                BottomBarItem(
                    id = 4,
                    image = Icons.Default.AccountCircle,
                    title = "Account",
                    isSelected = isSelected == 4
                )
            }
        }
    }
}


@Composable
fun BottomBarItem(
    id: Int,
    image: ImageVector,
    title: String,
    isSelected: Boolean
) {
    val tourModifier = Modifier.size(48.dp).tourStep(tourSteps[when (id) {
        1 -> 0
        2 -> 4
        3 -> 5
        else -> 6
    }], HighlightType.Circle)

    Box(
        modifier = tourModifier,
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = image,
                contentDescription = title,
                tint = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surfaceDim,
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .requiredSize(24.dp)
                    .padding( 0.dp)
            )
            Text(
                text = title,
                style = if (isSelected) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodySmall,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceDim
            )
        }
    }
}

val tourSteps = listOf(
    TourStep(0, "Click here to see the details on the home page and explore various giftcards."),
    TourStep(1, "Click here to  sell your  giftcards."),
    TourStep(2, "Click here to  make bills payments eg Data, Airtime, electricity,etc."),
    TourStep(3, "Click here to  refer and earn."),
    TourStep(4, "Click here to see the details of your funds."),
    TourStep(5, "Click here to earn more with each transaction."),
    TourStep(6, "Click here to see the details of your account.")
)

data class TourStep(val index: Int, val message: String)

@SuppressLint("SuspiciousModifierThen")
fun Modifier.tourStep(step: TourStep, highlightType: HighlightType): Modifier = this.then(
    tooltipAnchor {
        TooltipModel(
            index = step.index,
            title = { Text("") },
            message = { Text(step.message) },
            extraMessage = { Text("") },
            highlightType = highlightType
        )
    }
)