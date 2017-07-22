package com.github.kotlin.everywhere

import com.github.kotlin.everywhere.ktqunit.asyncTest
import com.github.kotlin.everywhere.ktqunit.fixture
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestBasics {
    @Test
    fun testAsyncTest() {
        asyncTest { resolve, _ ->
            assertTrue(true, "asyncTest Called")
            resolve(Unit)
        }
    }

    @Test
    fun testFixture() {
        val fixture = fixture()
        fixture.textContent = "fixture-content"
        assertEquals("fixture-content", fixture.textContent)
    }
}