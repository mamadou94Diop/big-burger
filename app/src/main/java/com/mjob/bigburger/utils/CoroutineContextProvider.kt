package com.mjob.bigburger.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider @Inject constructor() {
    open val io : CoroutineContext = Dispatchers.IO
    open val main: CoroutineContext = Dispatchers.Main
}