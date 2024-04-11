package template.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DynamicNavigationContainer(
    navigationItems: List<NavigationTabDisplayModel>,
    navigationType: NavigationType,
    modifier: Modifier = Modifier,
    appContent: @Composable () -> Unit,
) {
    when (navigationType) {
        NavigationType.BOTTOM_NAVIGATION -> {
            BottomNavigationContainer(navigationItems, modifier, appContent)
        }
        NavigationType.NAVIGATION_RAIL -> {
            NavigationRailContainer(navigationItems, modifier, appContent)
        }
        NavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawerContainer(navigationItems, modifier, appContent)
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
            .fillMaxSize(), // Should we use this, or should this also belong at call site
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
            .fillMaxSize(), // Should we use this, or should this also belong at call site
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
    DynamicNavigationContainer(
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
    DynamicNavigationContainer(
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
    DynamicNavigationContainer(
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
