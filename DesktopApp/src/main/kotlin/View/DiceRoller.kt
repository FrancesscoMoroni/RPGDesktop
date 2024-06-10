package View

import Animation.DiceAnimationRotate
import Animation.DiceAnimationStop
import Application.ApplicationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiceRollerView(applicationState: ApplicationState) {
    val items = listOf("d4", "d6", "d8", "d10", "d12", "d20")
    var selectedIndex by remember { mutableStateOf(applicationState.getDiceType()) }
    var numberOfDices by remember { mutableStateOf(1) }
    var animationState by remember {  mutableStateOf(false) }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        if (numberOfDices <= 5) {
            for (i in 1..numberOfDices) {
                if (animationState) {
                    DiceAnimationRotate(items[selectedIndex])
                } else {
                    DiceAnimationStop(items[selectedIndex])
                }
            }
        } else {
            for (i in 1..5) {
                if (animationState) {
                    DiceAnimationRotate(items[selectedIndex])
                } else {
                    DiceAnimationStop(items[selectedIndex])
                }
            }
        }
    }
    if(numberOfDices > 5) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            for (i in 6..numberOfDices) {
                if (animationState) {
                    DiceAnimationRotate(items[selectedIndex])
                } else {
                    DiceAnimationStop(items[selectedIndex])
                }
            }
        }
    }
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        items.forEachIndexed { index, s ->
            OutlinedButton(
                colors = if (selectedIndex == index) ButtonDefaults.buttonColors()
                    else ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.background),
                shape = CircleShape,
                elevation = if (selectedIndex == index) null
                    else ButtonDefaults.elevation(10.dp),
                onClick = {
                    selectedIndex = index
                    applicationState.setDiceType(index)
                }
            ) {
                Image(
                    painter = painterResource("/DiceImage/${s}/${s}_${s.drop(1)}.svg"),
                    contentDescription = null,
                    Modifier.defaultMinSize(
                        minWidth = 50.dp,
                        minHeight = 50.dp
                    )
                )
            }
        }
    }
    Row(
        Modifier.fillMaxWidth().padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        Column {
            Button(
                colors = ButtonDefaults.buttonColors(),
                onClick = {
                    if (numberOfDices != 1) {
                        numberOfDices--
                    }
                }
            ) {
                Icon(Icons.Filled.Remove, contentDescription = null)
            }
        }

        Column {
            Text(
                numberOfDices.toString(),
                fontSize = 30.sp
            )
        }

        Column {
            Button(
                onClick = {
                    if (numberOfDices != 10) {
                        numberOfDices++
                    }
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    }

    Row(
        Modifier.fillMaxWidth().padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        Button( onClick = {
            animationState = !animationState
        }) { Text(text = "Roll") }
    }
}