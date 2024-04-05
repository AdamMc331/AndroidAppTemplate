package template.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * An enumeration of all base navigation options for the user to see
 * on the main screen of the app.
 *
 * @property[icon] A reference to the [ImageVector] to display for this tab.
 * @property[textRes] A [StringRes] reference for the display text of this tab.
 *
 * TODO: Replace these enum values with the one specific to your application
 *  after cloning this template.
 */
enum class NavigationTab(
    val icon: ImageVector,
    @StringRes val textRes: Int,
) {
    ONE(
        icon = Icons.Default.Home,
        textRes = R.string.tab_one,
    ),
    TWO(
        icon = Icons.Default.Search,
        textRes = R.string.tab_two,
    ),
    THREE(
        icon = Icons.Default.Settings,
        textRes = R.string.tab_three,
    ),
    ;
}
