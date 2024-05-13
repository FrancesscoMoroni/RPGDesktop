package Window

import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState

class MainWindow(
    val title : String = "",
) {
    var undecorated : Boolean = false
    var placement = WindowPlacement.Fullscreen
    var maximized : Boolean = true

    fun changePlacement(newPlacement: WindowPlacement) {
        placement = newPlacement

        maximized = !maximized
    }
}