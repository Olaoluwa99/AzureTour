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
    var animType by remember { mutableStateOf(TourtipAnimType.Bouncy) }

    TourtipLayout(
        modifier = Modifier.fillMaxSize(),
        animType = animType,
        onClose = { currentStep ->
            // Handle close for event tracking $currentStep
        },
        onBack = { currentStep ->
            // Handle back for event tracking $currentStep
        },
        onNext = { currentStep ->
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
                    BottomNavBar()
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
                            .height(140.dp)
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
                    isSelected = currentRoute == 1
                )
                BottomBarItem(
                    id = 2,
                    image = Icons.Default.Favorite,
                    title = "Order",
                    isSelected = currentRoute == 2
                )
                BottomBarItem(
                    id = 3,
                    image = Icons.Default.Warning,
                    title = "Wallet",
                    isSelected = currentRoute == 3
                )
                BottomBarItem(
                    id = 4,
                    image = Icons.Default.AccountCircle,
                    title = "Account",
                    isSelected = currentRoute == 4
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
    val tourModifier = Modifier.tourStep(tourSteps[when (id) {
        1 -> 0
        2 -> 4
        3 -> 5
        else -> 6
    }], HighlightType.Circle)

    Column(
        modifier = tourModifier,
        horizontalAlignment = Alignment.CenterHorizontally
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

val tourSteps = listOf(
    TourStep(0, "Sell card", "Select this feature to enable you to sell your different gift cards.", ""),
    TourStep(1, "My Wallet", "You get to see your available balance, as well as your reward earnings here.", ""),
    TourStep(2, "Rate Calculator", "Select this feature to see your expected earning when you sell your gift card.", ""),
    TourStep(3, "Notification", "Select this feature to have access to all your notifications.", ""),
    TourStep(4, "Reward Center", "Select the VIP level feature to get access to the reward center.", ""),
    TourStep(5, "Reward Center", "Select the VIP level feature to get access to the reward center.", ""),
    TourStep(6, "Reward Center", "Select the VIP level feature to get access to the reward center.", ""),
    TourStep(7, "Reward Center", "You can always revisit this product tour anytime by accessing it in the Help section of the app.", "")
)

data class TourStep(val index: Int, val title: String, val message: String, val extraMessage: String = "")

@SuppressLint("SuspiciousModifierThen")
fun Modifier.tourStep(step: TourStep, highlightType: HighlightType): Modifier = this.then(
    tooltipAnchor {
        TooltipModel(
            index = step.index,
            title = { Text(step.title) },
            message = { Text(step.message) },
            extraMessage = { Text(step.extraMessage) },
            highlightType = highlightType
        )
    }
)