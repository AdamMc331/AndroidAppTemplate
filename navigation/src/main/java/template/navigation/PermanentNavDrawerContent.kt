package template.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val DRAWER_WIDTH = 200.dp
private val CONTENT_PADDING = 16.dp
private val TITLE_PADDING = 16.dp

/**
 * Given a collection of [navigationItems], render them inside a [PermanentDrawerSheet]
 * which will be used  as a fix set of navigation items on the side of the screen. Used
 * when we have a large screen width such as larger tablets.
 *
 * @see[PermanentNavDrawerContainer]
 */
@Composable
fun PermanentNavDrawerContent(
    navigationItems: List<NavigationTabDisplayModel>,
    modifier: Modifier = Modifier,
) {
    PermanentDrawerSheet(
        modifier = modifier.sizeIn(
            minWidth = DRAWER_WIDTH,
            maxWidth = DRAWER_WIDTH,
        ),
    ) {
        Column(
            modifier = Modifier
                .padding(CONTENT_PADDING)
                .verticalScroll(rememberScrollState()),
        ) {
            Text(
                modifier = Modifier
                    .padding(TITLE_PADDING),
                text = "APP_NAME".uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            navigationItems.forEach { tab ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            imageVector = tab.tab.icon,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(id = tab.tab.textRes),
                        )
                    },
                    selected = tab.selected,
                    onClick = tab.onClick,
                )
            }
        }
    }
}

@Composable
@Preview
private fun PermanentNavDrawerContentPreview() {
    PermanentNavDrawerContent(
        navigationItems = previewTabDisplayModels(),
    )
}
