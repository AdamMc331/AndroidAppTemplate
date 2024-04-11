# Dynamic Navigation

The purpose of this module is to provide a holistic solution to dynamic navigation based on the window width of an activity. For compact devices, like phones, we'll show a BottomNavigationBar at the bottom of the screen. For medium size devices, like foldables and small tablets, we'll show a SideNavigationRail on the left side of the screen. For large tablets and bigger screens, we'll show a PermanentNavigationDrawer on the left side of the screen (this looks like an expanded navigation rail). 

# Setup

First, open the NavigationTab.kt file, and you'll find an enum of sample tabs that can appear in an application. Go ahead and rename these enum entries and their properties to whatever information you want your tabs in your application to have. 

## Creating NavigationTabDisplayModel

While the above enum is a key for the finite tabs that can be displayed, when they are actually shown to the user there is more information we need, such as whether a tab is selected and what to do when it is clicked. You can create this setup by managing an app state. Below one is example of achieving this behavior:

```kotlin
var selectedTab by remember { mutableStateOf(NavigationTab.ONE) }

val tabDisplayModels = listOf(
    NavigationTabDisplayModel(
        tab = NavigationTab.ONE, // Enum value
        selected = (selectedTab == NavigationTab.ONE), // Selected if state equals enum value
        onClick = {
            selectedTab = SelectedTab.ONE // On click, set state to our enum value
        },
    ),
    // Repeat for other tabs
)
```

## Determining NavigationType

The NavigationType enum is all of the unique navigation options available to us. These are based on the width of the activity, but thankfully you don't need to do anything. Just be sure to call `NavigationType.fromActivity()` from within your host activity to get the navigation type that is necessary for your setup. 

## Implementing DynamicNavigationContainer

Once you have determined the tabs to display and the navigation type, we can call our DynamicNavigationContainer with the above information:

```kotlin
class MainActivity : ComponentActivity() {
    // ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                val tabDisplayModels = // ...
                
                DynamicNavigationContainer(
                    navigationItems = tabDisplayModels,
                    navigationType = NavigationType.from(this),
                ) {
                    // Render content based on selectedTab
                    // Ideally you should pass `Modifier.fillMaxSize()` with your content to 
                    // take up all available space. Or modify DynamicNavigationContainer to do this for you. 
                }
            }
        }
    }
}
```