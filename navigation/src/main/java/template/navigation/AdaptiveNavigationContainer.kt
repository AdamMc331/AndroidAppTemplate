package template.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Suppress("MaxLineLength") // Doc link too long to truncate
/**
 * This is the main entry point to adaptive navigation. Based on the [navigationType] supplied, we'll render
 * a specific navigation component to show the [navigationItems]. The space not used by the navigation component
 * will be used to render the [appContent].
 *
 * Note that it's important we reference [movableContentOf], as this ensures the lifecycle of [appContent] is maintained,
 * even if the [navigationType] changes.
 * See also: https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-component-api-guidelines.md#lifecycle-expectations-for-slot-parameters
 */
@Composable
fun AdaptiveNavigationContainer(
    navigationItems: List<NavigationTabDisplayModel>,
    navigationType: NavigationType,
    modifier: Modifier = Modifier,
    appContent: @Composable () -> Unit,
) {
    val content = remember(appContent) { movableContentOf(appContent) }

    when (navigationType) {
        NavigationType.BOTTOM_NAVIGATION -> {
            BottomNavigationContainer(navigationItems, modifier, content)
        }
        NavigationType.NAVIGATION_RAIL -> {
            NavigationRailContainer(navigationItems, modifier, content)
        }
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawerContainer(navigationItems, modifier, content)
        }
    }
}

@Composable
private fun PermanentNavigationDrawerContainer(
    navigationItems: List<NavigationTabDisplayModel>,
    modifier: Modifier,
    appContent: @Composable () -> Unit,
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentNavDrawerContent(navigationItems)
        },
        content = appContent,
        modifier = modifier,
    )
}

@Composable
private fun BottomNavigationContainer(
    navigationItems: List<NavigationTabDisplayModel>,
    modifier: Modifier,
    appContent: @Composable () -> Unit,
) {
    Column(
        modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .weight(1F),
        ) {
            appContent()
        }

        BottomNavigationBar(
            navigationItems = navigationItems,
        )
    }
}

@Composable
private fun NavigationRailContainer(
    navigationItems: List<NavigationTabDisplayModel>,
    modifier: Modifier,
    appContent: @Composable () -> Unit,
) {
    Row(
        modifier
            .fillMaxSize(),
    ) {
        SideNavigationRail(
            navigationItems = navigationItems,
        )

        Box(
            modifier = Modifier
                .weight(1F),
        ) {
            appContent()
        }
    }
}

@Preview(
    device = Devices.PIXEL_7_PRO
)
@Composable
private fun BottomNavigationPreview() {
    AdaptiveNavigationContainer(
        navigationItems = previewTabDisplayModels(),
        navigationType = NavigationType.BOTTOM_NAVIGATION,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("App Content")
        }
    }
}

@Preview(
    device = Devices.PIXEL_FOLD,
)
@Composable
private fun NavigationRailPreview() {
    AdaptiveNavigationContainer(
        navigationItems = previewTabDisplayModels(),
        navigationType = NavigationType.NAVIGATION_RAIL,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("App Content")
        }
    }
}

@Preview(
    device = Devices.PIXEL_C,
)
@Composable
private fun NavigationDrawerPreview() {
    AdaptiveNavigationContainer(
        navigationItems = previewTabDisplayModels(),
        navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("App Content")
        }
    }
}
