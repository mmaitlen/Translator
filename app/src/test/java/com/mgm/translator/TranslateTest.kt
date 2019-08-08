package com.mgm.translator

import org.junit.Assert.assertEquals
import org.junit.Test

class TranslateTest {
    @Test
    fun simpleTranslate() {
        val translate = TranslatorImpl()
        assertEquals("a z", translate.translate("z a"))
    }

    @Test
    fun singleSentenceTranslate() {
        val translate = TranslatorImpl()
        assertEquals("this is a test", translate.translate("test a is this"))
    }

    @Test
    fun multipleSentenceTranslate() {
        val translate = TranslatorImpl()
        assertEquals("this is a test.and another test", translate.translate("test a is this.test another and"))
    }

}