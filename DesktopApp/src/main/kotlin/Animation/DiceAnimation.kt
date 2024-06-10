package Animation

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

private var animationRotation : Float = 0f

@Composable
fun DiceAnimationRotate(
    diceType: String,
    diceValue: Int
) {
    var currentRotation by remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(Unit) {
        rotation.animateTo(
            targetValue = currentRotation + 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 100,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            )
        ) {
            currentRotation = value
            animationRotation = value
        }
    }

    Image(
        painter = painterResource("DiceImage/${diceType}/${diceType}_${diceValue}.svg"),
        contentDescription = "TEST",
        modifier = Modifier.width(100.dp).height(100.dp).rotate(currentRotation)
    )
}

@Composable
fun DiceAnimationStop(
    diceType: String,
    diceValue: Int
) {
    var currentRotation by remember { mutableStateOf(animationRotation) }
    val rotation = remember { Animatable(currentRotation) }

    LaunchedEffect(Unit) {

        if (animationRotation != 0f) {
            currentRotation = 15f
            rotation.animateTo(
                targetValue = 360f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = LinearOutSlowInEasing,
                )
            ) {
                currentRotation = value
                animationRotation = value
            }
        }
    }

    Image(
        painter = painterResource("DiceImage/${diceType}/${diceType}_${diceValue}.svg"),
        contentDescription = "TEST",
        modifier = Modifier.width(100.dp).height(100.dp).rotate(currentRotation)
    )
}