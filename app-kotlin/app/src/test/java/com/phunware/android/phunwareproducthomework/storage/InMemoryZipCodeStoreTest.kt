package com.phunware.android.phunwareproducthomework.storage

import org.junit.Assert.assertEquals
import org.junit.Test

class InMemoryZipCodeStoreTest {

    @Test
    fun testGetZipCodes() {
        /** Arrange **/
        val objectUnderTest = InMemoryZipCodeStore()

        /** Exercise **/
        objectUnderTest.addZipCode("92029")
        objectUnderTest.addZipCode("92028")
        objectUnderTest.addZipCode("92027")

        /** Verify **/
        assertEquals(3, objectUnderTest.getZipCodes().size)
    }

    @Test
    fun testAddZipCode() {
        /** Arrange **/
        val objectUnderTest = InMemoryZipCodeStore()

        /** Exercise **/
        objectUnderTest.addZipCode("92029")

        /** Verify **/
        assertEquals(1, objectUnderTest.getZipCodes().size)
    }
}