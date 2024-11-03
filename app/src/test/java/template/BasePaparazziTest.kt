package template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.cash.paparazzi.Paparazzi
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.runner.RunWith
import template.theme.TemplateTheme

/**
 * This base class allows us to write Paparazzi tests that validate composable content in both light and dark theme
 * using a parameterized test. Just extend this base class and call [snapshot] with your composable content.
 */
@RunWith(TestParameterInjector::class)
abstract class BasePaparazziTest {
    @get:Rule
    @Suppress("ktlint:standard:backing-property-naming")
    val _paparazzi = Paparazzi()

    @TestParameter
    val useDarkTheme: Boolean = false

    /**
     * Validates the supplied [content] in both light and dark theme.
     */
    fun snapshot(content: @Composable () -> Unit) {
        _paparazzi.snapshot {
            TemplateTheme(
                darkTheme = useDarkTheme,
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    content()
                }
            }
        }
    }
}
