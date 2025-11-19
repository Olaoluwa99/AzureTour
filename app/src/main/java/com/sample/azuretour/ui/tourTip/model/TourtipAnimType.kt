package com.sample.azuretour.ui.tourTip.model

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.Dp

enum class TourtipAnimType(val label: String) {
    Bouncy(label = "Bouncy Animation"),
    Tween(label = "Tween Animation"),
    EaseInOut(label = "Ease In Out Animation"),
    FastOutSlowIn(label = "Fast Out Slow In Animation"),
    SpringHighDamping(label = "Spring High Damping Animation");
    fun animOf(type: TourtipAnimType): AnimationSpec<Dp> {
        return when (type) {
            Bouncy -> spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )

            Tween -> tween(
                durationMillis = 1000,
                easing = CubicBezierEasing(a = 0.5f, b = 1.4f, c = 0.5f, d = 1f)
            )

            EaseInOut -> tween(
                durationMillis = 300,
                easing = androidx.compose.animation.core.EaseInOut
            )

            FastOutSlowIn -> tween(
                durationMillis = 300,
                easing = FastOutSlowInEasing
            )

            SpringHighDamping -> spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        }
    }
}
