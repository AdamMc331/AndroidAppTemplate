package template.navigation

/**
 * This is an enumeration of all of the different ways we can structure navigation in the application.
 *
 * @property[BOTTOM_NAVIGATION] If selected, the application will use a bottom navigation bar on the screen with tabs
 * for the user to select which navigate to a corresponding screen.
 * @property[NAVIGATION_RAIL] If selected, the application will use a side-pinned navigation rail with tabs for the
 * user to select which navigate to a corresponding screen.
 * @property[PERMANENT_NAVIGATION_DRAWER] If selected, the application will use a permanent navigation drawer on the
 * side of the screen.
 */
enum class NavigationType {
    BOTTOM_NAVIGATION,
    NAVIGATION_RAIL,
    PERMANENT_NAVIGATION_DRAWER,
}
