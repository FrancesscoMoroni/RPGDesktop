package View

import androidx.compose.material.Icon
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.*

val items = listOf("Losowanie", "Mapa", "Ekwipunek", "Ustawienia")
val icons = listOf(Icons.Filled.Casino, Icons.Filled.Map, Icons.Filled.Backpack, Icons.Filled.Settings)

@Composable
fun Navigation() {
    var selectedItem by remember { mutableIntStateOf(0) }

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
