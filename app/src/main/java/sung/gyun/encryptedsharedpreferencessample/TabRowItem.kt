package sung.gyun.encryptedsharedpreferencessample

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class TabRowItem(
    val title: String,
    val icon: ImageVector,
    val background: Color,
    val screen: @Composable () -> Unit
)
