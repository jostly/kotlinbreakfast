package se.citerus.kotlinbreakfast.rest

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.citerus.kotlinbreakfast.domain.Dictionary

/* A data class representing the JSON response */

data class PhraseDto(val adjective: String, val animal: String)

/* The REST controller responding to queries */

@RestController
@RequestMapping("/")
class PhraseController(
    val adjectives: Dictionary,
    val animals: Dictionary
) {

    /* Return a fully random phrase */

    @GetMapping(path = ["random"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun randomPhrase(): PhraseDto {
        return PhraseDto(adjectives.pickRandom(), animals.pickRandom())
    }

    /* Return a phrase with both words starting with the same, random, letter */

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun alliterativePhrase(): ResponseEntity<PhraseDto> {
        val adjective = adjectives.pickRandom()
        val animal = animals.startingWith(adjective[0])

        return animal
            ?.let { PhraseDto(adjective, animal) }
            .toResponseEntity()
    }

    /* Return a phrase with both words starting with the same, chosen, letter */

    @GetMapping(path = ["{c:[a-z]}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun startingWithPhrase(@PathVariable("c") char: Char): ResponseEntity<PhraseDto> {
        val adjective = adjectives.startingWith(char)
        val animal = animals.startingWith(char)

        val phrase = if (adjective != null && animal != null) {
            PhraseDto(adjective, animal)
        } else {
            null
        }

        return phrase.toResponseEntity()
    }

    /* Extension function to turn a nullable type into a ResponseEntity of that type */

    private fun <T> T?.toResponseEntity(): ResponseEntity<T> {
            return this
                ?.let { ResponseEntity.ok(it) }
                ?: ResponseEntity.notFound().build()
    }
}

