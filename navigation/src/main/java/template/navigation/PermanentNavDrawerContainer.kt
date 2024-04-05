package template.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PermanentNavDrawerContainer(
    navigationItems: List<NavigationTabDisplayModel>,
    content: @Composable () -> Unit,
) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentNavDrawerContent(navigationItems)
        },
        content = content,
    )
}

@Composable
@Preview
private fun PermanentNavDrawerContainerPreview() {
    PermanentNavDrawerContainer(
        navigationItems = previewTabDisplayModels(),
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(
                    text = "PREVIEW",
                )
            }
        },
    )
}
