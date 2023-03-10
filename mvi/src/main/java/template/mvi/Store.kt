package template.mvi

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * A [Store] is a state container for a given screen or application. This is intended to be the single
 * source of truth, for some type of [State]. This is the only class that can
 * modify the state, which it does via its own [reducer].
 *
 * The benefits of a single source of truth are that we have limited access to modifying our state, this can
 * help avoid situations such as race conditions or unexpected state changes.
 */
class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
) {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    fun dispatch(action: A) {
        _state.update { currentState ->
            reducer.reduce(action, currentState)
        }
    }
}
