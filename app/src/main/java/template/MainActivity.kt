package template

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import template.navigation.DynamicNavigationContainer
import template.navigation.NavigationTab
import template.navigation.NavigationTabDisplayModel
import template.navigation.NavigationType
import template.theme.TemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("ADAMLOG", "OnCreate")

        setContent {
            ConfigureEdgeToEdgeWindow()

            AdaptiveNavigationExample()
        }
    }

    @Composable
    private fun AdaptiveNavigationExample() {
        TemplateTheme {
            var selectedTab by rememberSaveable {
                mutableStateOf(NavigationTab.ONE)
            }

            val tabs = NavigationTab.entries.map { tab ->
                NavigationTabDisplayModel(
                    tab = tab,
                    selected = (selectedTab == tab),
                    onClick = {
                        selectedTab = tab
                    },
                )
            }

            DynamicNavigationContainer(
                navigationItems = tabs,
                navigationType = NavigationType.fromActivity(activity = this),
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                ) {
                    var isChecked by rememberSaveable {
                        mutableStateOf(false)
                    }

                    Column {
                        Text(text = "Checked: $isChecked")

                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = {
                                isChecked = !isChecked
                            },
                        )
                    }
                }
            }
        }
    }

    /**
     * Enables edge to edge support of this activity.
     *
     * If you'd like, you can override the default behavior of system bars by
     * customizing the parameters in the call to [enableEdgeToEdge].
     */
    @Composable
    private fun ConfigureEdgeToEdgeWindow() {
        DisposableEffect(Unit) {
            enableEdgeToEdge()
            onDispose {}
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
