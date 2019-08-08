package com.mgm.translator

import java.lang.StringBuilder
import java.util.Stack

interface Translator {
    fun translate(original: String): String
}

/**
 * Translates input string by moving from the end of the input to the beginning
 * adding words to the output string as spaces are encountered.
 *
 * A Stack is utilized to hold StringBuilders to handle multiple sentences, delineated
 * with '.'.  The StringBuilder at the top of the Stack holds the sentence currently
 * being worked on.
 */
class TranslatorImpl: Translator {
    override fun translate(original: String): String {
        if (original.isEmpty()) return ""

        val sentenceStack = Stack<StringBuilder>()
        val lastCharacter = original.length - 1
        var idx2 = original.length

        // add an initial StringBuilder to the stack to hold the initial sentence
        sentenceStack.push(StringBuilder())

        // common functionality to add a word to the current sentence
        fun addWord(word: String) {
            if (sentenceStack.peek().isNotEmpty()) {
                sentenceStack.peek().append(" ")
            }
            sentenceStack.peek().append(word)
        }

        for (idx1 in lastCharacter downTo 0) {

            // common functionality to add word from substring of original
            fun addSubstringFromOriginal() {
                addWord(original.substring(idx1 + 1, idx2))
                idx2 = idx1
            }

            when(original.get(idx1)) {
                ' ' -> {
                    addSubstringFromOriginal()
                }
                '.' -> {
                    addSubstringFromOriginal()
                    // seems like a bit of an overkill...more efficient solution
                    sentenceStack.push(StringBuilder("."))
                    sentenceStack.push(StringBuilder(""))
                }
            }
        }
        addWord(original.substring(0, idx2))
        val final = StringBuilder()

        while(sentenceStack.isNotEmpty()) {
            final.append(sentenceStack.pop())
        }
        return final.toString()
    }

}