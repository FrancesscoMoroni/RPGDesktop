import Application.ApplicationState
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinx.coroutines.launch

var applicationState : ApplicationState =  ApplicationState()

@Composable
@Preview
fun App() {

    Button(onClick = { applicationState.changeWindowMode() }) {Text("FullScreen")}

    /*val colors = listOf(
        Color(0xFFffd7d7.toInt()),
        Color(0xFFffe9d6.toInt()),
        Color(0xFFfffbd0.toInt()),
        Color(0xFFe3ffd9.toInt()),
        Color(0xFFd0fff8.toInt())
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {

        var selectedItem by remember { mutableIntStateOf(0) }
        val items = listOf("Losowanie", "Mapa", "Ekwipunek")
        val icons = listOf(Icons.Filled.Casino, Icons.Filled.Map, Icons.Filled.Backpack)

        Scaffold(topBar = {
            TopAppBar(title = { Text("Testowa aplikacja") })
        }, content = {
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
        })

    }*/
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
