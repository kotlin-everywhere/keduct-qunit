package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.ktqunit.asyncTest
import org.junit.Test
import kotlin.test.assertTrue

class TestBasics {
    @Test
    fun testAsyncTest() {
        asyncTest { resolve, _ ->
            assertTrue(true, "asyncTest Called")
            resolve(Unit)
        }
    }
}