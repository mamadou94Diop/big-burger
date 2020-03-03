package helpers

import com.mjob.bigburger.utils.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TestCoroutineContextProvider @Inject constructor() : CoroutineContextProvider() {
    override val io : CoroutineContext = Dispatchers.Unconfined
    override val main: CoroutineContext = Dispatchers.Unconfined
}