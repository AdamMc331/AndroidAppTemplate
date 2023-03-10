package template.mvi

/**
 * A reduce is responsible for consuming some [Action], as well as the current [State], and using that
 * to determine how to output a new [State].
 *
 * By defining generic types here, we can allow the users to define a reducer per screen/flow.
 * Example: `class ProfileScreenReducer : Reducer<ProfileScreenState, ProfileScreenAction>`
 */
interface Reducer<S : State, A : Action> {

    /**
     * @see [Reducer]
     */
    fun reduce(currentState: S, action: A): S
}
