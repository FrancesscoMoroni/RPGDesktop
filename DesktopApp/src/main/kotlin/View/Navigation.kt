package View

import Application.ApplicationState
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.*

val items = listOf("Losowanie", "Mapa", "Ekwipunek", "Ustawienia")
val icons = listOf(Icons.Filled.Casino, Icons.Filled.Map, Icons.Filled.Backpack, Icons.Filled.Settings)

@Composable
fun Navigation(applicationState : ApplicationState, selectedNavigationItem : MutableState<Int>) {
    NavigationRail {
        items.forEachIndexed { index, item ->
            NavigationRailItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedNavigationItem.value == index,
                onClick = { selectedNavigationItem.value = index }
            )
        }

        NavigationRailItem(
            icon = { Icon(Icons.Filled.Close, contentDescription = "Wyjdź") },
            label = { Text("Wyjdź") },
            selected = false,
            onClick = { applicationState.exitApplication() }
        )
    }
}
