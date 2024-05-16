package View

import Application.ApplicationState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiceRollerView(applicationState: ApplicationState) {
    val items = listOf("d4", "d6", "d8", "d10", "d12", "d20", "d100")
    var selectedIndex by remember { mutableStateOf(applicationState.getDiceType()) }

    Column(
        Modifier.padding(10.dp)
    ) {
        items.forEachIndexed { index, s ->
            RadioButton(
                selected = (selectedIndex == index),
                onClick = {
                    selectedIndex = index
                    applicationState.setDiceType(index)
                }
            )
            Text(s)
        }
    }
}