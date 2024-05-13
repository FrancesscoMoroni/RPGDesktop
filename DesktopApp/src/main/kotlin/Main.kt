import Application.ApplicationState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.launch

var applicationState : ApplicationState =  ApplicationState()

@Composable
@Preview
fun App() {
    val checkedState = remember { mutableStateOf(true) }

    MaterialTheme {

        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf("Losowanie", "Mapa", "Ekwipunek")
        val icons = listOf(Icons.Filled.Casino, Icons.Filled.Map, Icons.Filled.Backpack)

        Scaffold(topBar = {
            TopAppBar(title = { Text("Testowa aplikacja") })
        }) {
            Row() {
                Column(
                ) {
                    NavigationRail {
                        items.forEachIndexed { index, item ->
                            NavigationRailItem(
                                icon = { Icon(icons[index], contentDescription = item) },
                                label = { Text(item) },
                                selected = selectedItem == index,
                                onClick = { selectedItem = index }
                            )
                        }
                    }
                }
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
