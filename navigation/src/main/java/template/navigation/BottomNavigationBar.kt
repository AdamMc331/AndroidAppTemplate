package template.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

/**
 * Given a collection of [navigationItems], render a [NavigationBar] which is a standard
 * bottom navigation used on compact screen sizes.
 */
@Composable
fun BottomNavigationBar(
    navigationItems: List<NavigationTabDisplayModel>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        navigationItems.forEach { navigationItem ->
            BottomNavigationItem(navigationItem)
        }
    }
}

/**
 * Creates a [NavigationBarItem] from the supplied [navigationItem].
 */
@Composable
private fun RowScope.BottomNavigationItem(
    navigationItem: NavigationTabDisplayModel,
) {
    NavigationBarItem(
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

@Preview
@Composable
private fun BottomNavigationPreview() {
    BottomNavigationBar(
        navigationItems = previewTabDisplayModels(),
    )
}
