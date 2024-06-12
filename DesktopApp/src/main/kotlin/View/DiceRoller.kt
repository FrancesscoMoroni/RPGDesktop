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
import kotlin.random.Random

@Composable
fun DiceRollerView(applicationState: ApplicationState) {
    val items = listOf("d4", "d6", "d8", "d10", "d12", "d20")
    val dices = listOf(4, 6, 8, 10, 12, 20)
    val defaultDiceColumnSize = 150.dp
    val defaultDiceTypeColumnSize = 50.dp

    var result by remember { mutableStateOf(0) }
    var diceValues by remember { mutableStateOf(listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)) }
    var selectedIndex by remember { mutableStateOf(applicationState.getDiceType()) }
    var numberOfDices by remember { mutableStateOf(1) }
    var animationState by remember { mutableStateOf(false) }
    var rolled by remember { mutableStateOf(true) }
    var calculateResult by remember { mutableStateOf(true) }

    if (numberOfDices <= 5) {
        Row(
            Modifier.fillMaxWidth().defaultMinSize(minHeight = defaultDiceColumnSize * 2),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..numberOfDices) {
                if (animationState) {
                    DiceAnimationRotate(items[selectedIndex], diceValues[i - 1])
                } else {
                    DiceAnimationStop(items[selectedIndex], diceValues[i - 1])
                }
            }
        }
    } else {
        Row(
            Modifier.fillMaxWidth().defaultMinSize(minHeight = defaultDiceColumnSize),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 1..5) {
                if (animationState) {
                    DiceAnimationRotate(items[selectedIndex], diceValues[i - 1])
                } else {
                    DiceAnimationStop(items[selectedIndex], diceValues[i - 1])
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().defaultMinSize(minHeight = defaultDiceColumnSize),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 6..numberOfDices) {
                if (animationState) {
                    DiceAnimationRotate(items[selectedIndex], diceValues[i - 1])
                } else {
                    DiceAnimationStop(items[selectedIndex], diceValues[i - 1])
                }
            }
        }
    }

    Row(
        Modifier.fillMaxWidth().defaultMinSize(minHeight = defaultDiceTypeColumnSize),
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
                    if (!animationState) {
                        selectedIndex = index
                        applicationState.setDiceType(index)
                    }
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
            Text(
                result.toString(),
                fontSize = 30.sp
            )
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
                    if (numberOfDices != 1 && !animationState) {
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
                    if (numberOfDices != 10 && !animationState) {
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
        Button(
            colors = if (!animationState) ButtonDefaults.buttonColors()
            else ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.inversePrimary),
            onClick = {
                animationState = !animationState
            }) {
            if (animationState) {
                if (!rolled) {
                    result = 0
                    var randomValues = listOf<Int>()
                    diceValues.forEach {
                        randomValues = randomValues.plus(Random.nextInt(1, dices[selectedIndex] + 1))
                    }
                    diceValues = randomValues
                    rolled = true
                }
                calculateResult = true
                Text(text = "Stop")
            } else {
                rolled = false
                if (calculateResult) {
                    for (i in 1..numberOfDices) {
                        result += diceValues[i - 1]
                    }
                }
                calculateResult = false
                Text(text = "Roll")
            }
        }
    }
}