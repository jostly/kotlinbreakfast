package se.citerus.kotlinbreakfast.domain

import kotlin.random.Random

/* Dictionary class holds a list of words and can serve up random words */

class Dictionary(words: List<String>) {

    /* Internal data structure */

    private val dict = words
        .filter { it.length > 1 }
        .map { it.toLowerCase() }
        .toTypedArray()

    /* Pick random word */

    fun pickRandom(): String {
        return dict[Random.nextInt(dict.size)]
    }

    /* Pick word starting with a specific letter */

    fun startingWith(c: Char): String? {
        val tempDict = Dictionary(dict.filter { it.startsWith(c) })
        return if (tempDict.isEmpty()) {
            null
        } else {
            tempDict.pickRandom()
        }
    }

    /* Is the dictionary empty? */

    private fun isEmpty() = dict.isEmpty()
}