package com.github.kotlin.everywhere.ktqunit

import com.github.kotlin.everywhere.ktbluebird.Bluebird

external private interface QUnitAssert {
    fun async(): () -> Unit
}

external private interface QUnitTest {
    val assert: QUnitAssert
    fun pushFailure(message: String, stackTrace: dynamic)
}

external private interface QUnitConfig {
    val current: QUnitTest
}

external private object QUnit {
    val config: QUnitConfig = definedExternally
}

fun asyncTest(promise: Bluebird<*>) {
    promise
            .catch { e ->
                @Suppress("UnsafeCastFromDynamic")
                QUnit.config.current.pushFailure(e.message, e.stack)
                @Suppress("UnsafeCastFromDynamic")
                throw e
            }
            .finally(QUnit.config.current.assert.async())
}

fun asyncTest(body: ((Unit) -> kotlin.Unit, (kotlin.Exception) -> kotlin.Unit) -> Unit) {
    asyncTest(Bluebird(body))
}