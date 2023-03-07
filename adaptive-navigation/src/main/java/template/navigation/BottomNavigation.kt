package template.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigation(
    tabs: List<NavigationTab>,
    selectedTab: NavigationTab,
    onTabClicked: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        tabs.forEach { tab ->
            val tabText = stringResource(tab.textRes)

            NavigationBarItem(
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
private fun BottomNavigationPreview() {
    val tabs = listOf(
        NavigationTab.TabOne,
        NavigationTab.TabTwo,
        NavigationTab.TabThree,
    )

    BottomNavigation(
        tabs = tabs,
        selectedTab = NavigationTab.TabOne,
        onTabClicked = {},
    )
}
