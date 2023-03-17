# MVI Module

This module includes some core MVI related interfaces, that allow you to jumpstart an MVI architecture setup in your Android application using this template.

## Example Setup

To use all of the relevant interfaces for a given screen, such as a `ProfileScreen`, you may end up with a structure that looks similar to this. 

### Action

```kotlin
sealed class ProfileScreenAction : Action {
    data class SetLoading(
        val id: String,
    ) : ProfileScreenAction()
    
    data class ProfileLoaded(
        val profile: Profile,
    ) : ProfileScreenAction()
    
    // ...
}
```

### State

```kotlin
data class ProfileScreenState(
    val isLoading: Boolean,
    val profile: Profile?,
)
```

### Reducer

```kotlin
class ProfileScreenReducer : Reducer<ProfileScreenState, ProfileScreenAction> {
    
    override fun reduce(currentState: ProfileScreenState, action: ProfileScreenAction) {
        return when (action) {
            is ProfileScreenAction.FetchProfile -> {
                // Fetch profile
                currentState.copy(
                    isLoading = true,
                )
            }
            is ProfileScreenAction.ProfileLoaded -> {
                currentState.copy(
                    isLoading = false,
                    profile = action.profile,
                )
            }
        }
    }
}
```

### Store

```kotlin
val profileScreenStore = Store(
    initialState = ProfileScreenState(isLoading = true, profile = null),
    reducer = ProfileScreenReducer(),
)
```

## Architecture Usage

Once all of your components are defined, you can hook this up by creating a store inside a `ViewModel` or similar controller type component, and connect the store with any UI interactions. For example:

```kotlin
class ProfileViewModel : ViewModel() {
    private val profileScreenStore = Store(
        initialState = ProfileScreenState(isLoading = true, profile = null),
        reducer = ProfileScreenReducer(),
    )
    
    init {
        profileScreenStore.dispatch(
            ProfileScreenAction.FetchProfile("12345")
        )
    }
}
```

## Data Layer

TODO: Adam to write up an example on how we can implement a data layer in an MVI world that can consume an action like `FetchProfile` and then emit a `ProfileLoaded` action once that completes. 