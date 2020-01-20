package se.citerus.kotlinbreakfast

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/** Spring Boot application class */

@SpringBootApplication
class PhraserApplication

/**
 *
 * Main method for the application, in the static context of the file
 *
 * Declaring a function like this, outside a class, will create a class called <filename>Kt.class to put the
 * method on.
 *
 * In our case, this is equivalent to the Java code
 *
 * public class PhraserApplicationKt {
 *
 *  public static void main(args: String...) {
 *      ...
 *  }
 *
 * }
 *
 * This is important to know in cases where you need to refer to the FQCN of the main method
 *
 **/

fun main(vararg args: String) {
    runApplication<PhraserApplication>(*args)
}

