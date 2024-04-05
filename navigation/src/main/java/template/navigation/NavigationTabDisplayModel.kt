package template.navigation

/**
 * A user friendly representation of a navigation item that appears in either
 * a bottom navigation or a navigation rail.
 *
 * @property[tab] The [NavigationTab] currently being viewed by the user.
 * @property[selected] If true, this navigation item will show a selected state.
 * @property[onClick] A callback to trigger when the user taps on this navigation item.
 */
data class NavigationTabDisplayModel(
    val tab: NavigationTab,
    val selected: Boolean,
    val onClick: () -> Unit,
)

/**
 * Helper function to return a list of [NavigationTabDisplayModel] entities
 * for Compose previews.
 */
internal fun previewTabDisplayModels(): List<NavigationTabDisplayModel> {
    return listOf(
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
}
