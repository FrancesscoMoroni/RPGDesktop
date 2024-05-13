package Window

import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState

class MainWindow(
    val title : String = "",
) {
    var undecorated : Boolean = true
    var placement = WindowPlacement.Maximized
    var maximized : Boolean = true

    fun changePlacement(newPlacement: WindowPlacement) {
        placement = newPlacement
    }

    fun changeUndecorated() {
        undecorated = !undecorated
    }
}