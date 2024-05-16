import Application.ApplicationState
import View.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*

var applicationState : ApplicationState =  ApplicationState()

@Composable
@Preview
fun App() {
    val selectedNavigationItem = remember { mutableIntStateOf(0) }

    MaterialTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text("RPGDesktop") })
        }) {
            Row() {
                Column(
                ) {
                    Navigation(applicationState, selectedNavigationItem)
                }
                when(selectedNavigationItem.value) {
                    0 -> DiceRollerView(applicationState)
                    1 -> MapView()
                    2 -> EquipmentView()
                    3 -> SettingsView(applicationState)
                }
            }
        }
    }
}

fun main() = application {

    val window = Window(onCloseRequest = ::exitApplication,
        title = applicationState.windowState.title,
        state = WindowState(applicationState.windowState.placement),
        undecorated = applicationState.windowState.undecorated
    ) {
        applicationState.window = window
        App()
    }
}
