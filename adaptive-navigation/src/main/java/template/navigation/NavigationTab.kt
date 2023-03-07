package template.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationTab(
    val icon: ImageVector,
    val textRes: Int,
) {
    object TabOne : NavigationTab(
        icon = Icons.Default.Home,
        textRes = R.string.tab_one,
    )

    object TabTwo : NavigationTab(
        icon = Icons.Default.AccountCircle,
        textRes = R.string.tab_two,
    )

    object TabThree : NavigationTab(
        icon = Icons.Default.Settings,
        textRes = R.string.tab_three,
    )
}
