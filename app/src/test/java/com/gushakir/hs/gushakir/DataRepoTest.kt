package com.gushakir.hs.gushakir

import com.gushakir.hs.gushakir.data.DataRepoImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class DataRepoTest {
    @Test
    fun testGetData() {
        val repo = DataRepoImpl()
        assertEquals("sample data", repo.getData())
    }
}
