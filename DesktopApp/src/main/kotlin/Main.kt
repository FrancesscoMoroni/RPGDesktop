import Application.ApplicationState
import View.Navigation
import View.Settings
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
    MaterialTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Testowa aplikacja") })
        }) {
            Row() {
                Column(
                ) {
                    Navigation()
                }
                Settings(applicationState)
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
