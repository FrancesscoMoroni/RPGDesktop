package Application

import Window.MainWindow
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.WindowPlacement
import kotlin.system.exitProcess

class ApplicationState {
    val windowState = MainWindow("RPGDesktop")
    var window: ComposeWindow = ComposeWindow()
    var diceSetting: DiceSetting = DiceSetting()

    fun changeWindowMode() {
        if (windowState.maximized) {
            windowState.changePlacement(WindowPlacement.Floating)
        } else {
            windowState.changePlacement(WindowPlacement.Fullscreen)
        }

        window.placement = windowState.placement
    }

    fun exitApplication() {
        exitProcess(0)
    }

    fun ifMaximized(): Boolean {
        return windowState.maximized
    }

    fun changeDiceSetting(type: Int, amount: Int) {
        diceSetting.diceType = type
        diceSetting.diceAmount = amount
    }

    fun getDiceType() : Int {
        return diceSetting.diceType
    }

    fun setDiceType(type: Int) {
        diceSetting.diceType = type
    }


}