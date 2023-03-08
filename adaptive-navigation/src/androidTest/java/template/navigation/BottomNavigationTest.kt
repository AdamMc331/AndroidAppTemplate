package template.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun renderBottomNavigation() {
        val tabs = listOf(
            NavigationTab.TabOne,
            NavigationTab.TabTwo,
            NavigationTab.TabThree,
        )

        val selectedTab = NavigationTab.TabOne

        composeTestRule.setContent {
            BottomNavigation(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabClicked = {},
            )
        }

        composeTestRule.onNodeWithText("One").assertIsDisplayed()
        composeTestRule.onNodeWithText("Two").assertIsDisplayed()
        composeTestRule.onNodeWithText("Three").assertIsDisplayed()

        composeTestRule.onNodeWithText("One").assertIsSelected()
        composeTestRule.onNodeWithText("Two").assertIsNotSelected()
        composeTestRule.onNodeWithText("Three").assertIsNotSelected()
    }

    @Test
    fun handleTabClicked() {
        val tabs = listOf(
            NavigationTab.TabOne,
            NavigationTab.TabTwo,
            NavigationTab.TabThree,
        )

        val selectedTab = NavigationTab.TabOne

        var clickedTab: NavigationTab? = null

        composeTestRule.setContent {
            BottomNavigation(
                tabs = tabs,
                selectedTab = selectedTab,
                onTabClicked = { tab ->
                    clickedTab = tab
                },
            )
        }

        composeTestRule.onNodeWithText("One").assertIsSelected()
        composeTestRule.onNodeWithText("Two").assertIsNotSelected()

        composeTestRule.onNodeWithText("Two").performClick()

        assertEquals(NavigationTab.TabTwo, clickedTab)
    }
}
