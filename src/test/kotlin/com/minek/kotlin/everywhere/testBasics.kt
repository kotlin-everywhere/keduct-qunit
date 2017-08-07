package com.minek.kotlin.everywhere

import com.minek.kotlin.everywhere.keduct.qunit.asyncTest
import com.minek.kotlin.everywhere.keduct.qunit.fixture
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