package com.github.kotlin.everywhere.ktqunit

import com.github.kotlin.everywhere.ktbluebird.Bluebird
import org.w3c.dom.Element
import kotlin.browser.document

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

fun fixture(): Element =
        document.getElementById("qunit-fixture") ?: throw IllegalStateException("#qunit-fixture missing")

fun asyncTest(promise: Bluebird<*>) {
    promise
            .catch { e ->
                @Suppress("UnsafeCastFromDynamic")
                QUnit.config.current.pushFailure(e.message, e.stack)
            }
            .finally(QUnit.config.current.assert.async())
}

fun asyncTest(body: ((Unit) -> kotlin.Unit, (kotlin.Exception) -> kotlin.Unit) -> Unit) {
    asyncTest(Bluebird(body))
}