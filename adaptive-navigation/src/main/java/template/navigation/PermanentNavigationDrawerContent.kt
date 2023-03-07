package template.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@ExperimentalMaterial3Api
fun PermanentNavigationDrawerContent(
    tabs: List<NavigationTab>,
    selectedTab: NavigationTab,
    onTabClicked: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    PermanentDrawerSheet(
        modifier = modifier.sizeIn(
            minWidth = 200.dp,
            maxWidth = 300.dp,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            tabs.forEach { tab ->
                val tabText = stringResource(id = tab.textRes)

                NavigationDrawerItem(
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
}

@Preview
@Composable
@ExperimentalMaterial3Api
fun PermanentNavigationDrawerContentPreview() {
    val tabs = listOf(
        NavigationTab.TabOne,
        NavigationTab.TabTwo,
        NavigationTab.TabThree,
    )

    PermanentNavigationDrawerContent(
        tabs = tabs,
        selectedTab = NavigationTab.TabOne,
        onTabClicked = {},
    )
}
