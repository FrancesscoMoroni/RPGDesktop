package Application

import Window.MainWindow
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.WindowPlacement

class ApplicationState {
    val windowState = MainWindow("RPGDesktop")
    var window : ComposeWindow = ComposeWindow()

    fun changeWindowMode() {
        if (windowState.maximized) {
            windowState.changeUndecorated()
        } else {
            windowState.changeUndecorated()
            windowState.changePlacement(WindowPlacement.Maximized)
        }

        window.placement = windowState.placement
    }
}