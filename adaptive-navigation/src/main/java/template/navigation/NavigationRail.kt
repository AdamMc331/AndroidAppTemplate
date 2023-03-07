package template.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SideNavigation(
    tabs: List<NavigationTab>,
    selectedTab: NavigationTab,
    onTabClicked: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier,
    ) {
        tabs.forEach { tab ->
            val tabText = stringResource(tab.textRes)

            NavigationRailItem(
                selected = (tab == selectedTab),
                onClick = {
                    onTabClicked.invoke(tab)
                },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tabText,
                    )
                },
                label = {
                    Text(
                        text = tabText,
                    )
                },
            )
        }
    }
}

@Preview
@Composable
private fun SideNavigationPreview() {
    val tabs = listOf(
        NavigationTab.TabOne,
        NavigationTab.TabTwo,
        NavigationTab.TabThree,
    )

    SideNavigation(
        tabs = tabs,
        selectedTab = NavigationTab.TabOne,
        onTabClicked = {},
    )
}
