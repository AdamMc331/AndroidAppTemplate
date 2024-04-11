package template.navigation

import android.app.Activity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable

/**
 * This is an enumeration of all of the different ways we can structure navigation in the application.
 *
 * @property[BOTTOM_NAVIGATION] If selected, the application will use a bottom navigation bar on the screen with tabs
 * for the user to select which navigate to a corresponding screen.
 * @property[NAVIGATION_RAIL] If selected, the application will use a side-pinned navigation rail with tabs for the
 * user to select which navigate to a corresponding screen.
 * @property[PERMANENT_NAVIGATION_DRAWER] If selected, the application will use a permanent navigation drawer on the
 * side of the screen.
 */
enum class NavigationType {
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL,
    PERMANENT_NAVIGATION_DRAWER,
    ;
    
    companion object {
        /**
         * This function will look up a [WindowWidthSizeClass] for the given [activity], and then
         * based on the width of that activity, will provide a [NavigationType] to determine
         * the navigation solution to show to the user.
         */
        @Composable
        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        fun fromActivity(activity: Activity): NavigationType {
            val windowSizeClass = calculateWindowSizeClass(activity)

            return when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    BOTTOM_NAVIGATION
                }

                WindowWidthSizeClass.Medium -> {
                    NAVIGATION_RAIL
                }

                WindowWidthSizeClass.Expanded -> {
                    PERMANENT_NAVIGATION_DRAWER
                }

                else -> {
                    BOTTOM_NAVIGATION
                }
            }
        }
    }
}
