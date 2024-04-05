package template.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Given a collection of [navigationItems], render them inside a [NavigationRail]
 * which appears on the side of the device in medium size screens like foldables
 * and small tablets.
 */
@Composable
fun SideNavigationRail(
    navigationItems: List<NavigationTabDisplayModel>,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier,
    ) {
        navigationItems.forEach { navigationItem ->
            SideNavigationRailItem(navigationItem)
        }
    }
}

/**
 * Renders a [NavigationRailItem] for the supplied [navigationItem].
 */
@Composable
private fun SideNavigationRailItem(
    navigationItem: NavigationTabDisplayModel,
) {
    NavigationRailItem(
        icon = {
            Icon(
                imageVector = navigationItem.tab.icon,
                contentDescription = stringResource(navigationItem.tab.textRes),
            )
        },
        label = {
            Text(
                text = stringResource(navigationItem.tab.textRes),
            )
        },
        selected = navigationItem.selected,
        onClick = navigationItem.onClick,
    )
}

@Composable
@Preview
private fun NavigationRailPreview() {
    val navigationItems = listOf(
        NavigationTabDisplayModel(
            tab = NavigationTab.ONE,
            selected = true,
            onClick = {},
        ),
        NavigationTabDisplayModel(
            tab = NavigationTab.TWO,
            selected = false,
            onClick = {},
        ),
        NavigationTabDisplayModel(
            tab = NavigationTab.THREE,
            selected = false,
            onClick = {},
        ),
    )

    SideNavigationRail(navigationItems = navigationItems)
}
