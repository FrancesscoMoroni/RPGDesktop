package View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import  androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate

@Composable
fun MapView() {
    Column(
        Modifier.fillMaxSize()
    ) {
       Canvas(modifier = Modifier.fillMaxSize()) {
           translate(
               top = 800f
           ) {
               drawCircle(Color.Red, center = Offset(50f, 200f), radius = 40f)
           }
        }
    }
}
