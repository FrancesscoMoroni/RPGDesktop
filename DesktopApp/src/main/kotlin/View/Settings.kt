package View

import Application.ApplicationState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Settings(applicationState : ApplicationState) {
    val checkedState = remember { mutableStateOf(true) }

    Column(
        Modifier.padding(10.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    applicationState.changeWindowMode()
                }
            )
            Text ("FullScreen")
        }

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                onClick = {applicationState.exitApplication()},
            ) {
                Text("Exit Application")
            }
        }
    }

}
