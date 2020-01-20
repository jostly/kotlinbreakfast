package se.citerus.kotlinbreakfast

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import se.citerus.kotlinbreakfast.domain.Dictionary
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.streams.toList

/* Configuration class for the application */

@Configuration
@EnableConfigurationProperties
data class PhraserConfiguration(
    @Value("\${paths.adjectives}") val adjectivesPath: String,
    @Value("\${paths.animals}") val animalsPath: String
) {

    /* Bean for the animals dictionary */

    @Bean(name = ["animals"])
    fun animals(): Dictionary {
        return Dictionary(readResource(animalsPath))
    }

    /* Bean for the adjectives dictionary */

    @Bean(name = ["adjectives"])
    fun adjectives(): Dictionary {
        return Dictionary(readResource(adjectivesPath))
    }

    /* Function to read a resource into a list of strings */

    private fun readResource(path: String): List<String> =
        javaClass
            .getResourceAsStream(path)
            .toReader()
            .lines()
            .toList()

    /* Extension function to turn an InputStream to a BufferedReader */

    private fun InputStream.toReader(): BufferedReader =
        BufferedReader(InputStreamReader(this))
}