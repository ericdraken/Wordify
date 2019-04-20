# Wordify String Integer to English Words Converter

## Requirements

* Java 8+ (for streams)
* Apache Maven 3.8+
* Jetbrains Annotations 13+ (for @NotNull)
* JUnit 5+ (for parametric testing)

## Running

The easiest way to run Wordify is with the precompiled JAR. Make sure the Java executable is on the class path, then navigate to the `lib/` folder. On Linux, run:

    java -jar Wordify.jar
    
## REPL operation

When started, the console will prompt you to enter an integer:

    -------------------------------------------
    Welcome to Wordify version 1.0 (Java 9.0.1)
    -------------------------------------------
    Quit by entering 'q' or 'quit'. See this message again with 'h' or 'help'.
    Enter 'max' to see the largest number supported, or 'min' to see the smallest.
    Please enter an integer to wordify:
    
Enter an integer, even negative, and it will be converted into English words:

    123456789
    One hundred twenty three million four hundred fifty six thousand seven hundred and eighty nine

    -8274
    Negative eight thousand two hundred and seventy four

Valid characters are `0-9` and `-` only. Note `-0` is invalid because `0` is by definition a non-negative number.

### Maximum and minimum integers
    
The maximum integer supported is `10^1003-1`, and the minimum is `-10^1003-1`. In other words, Wordify supports up to 1 [millillion](http://www.olsenhome.com/bignumbers/) - 1, or one thousand and three nines.

### Help

Enter 'h' or 'help' at any time to see the help message.

### Min / max examples

To see what `10^1003-1` looks like, enter `max`. To see what `-10^1003-1` looks like, enter `min`.

### Quit

Enter 'q' or 'quit' (or 'x' or 'exit') to end the REPL session

## Hint System

If the integer entered is invalid, a hint about why will be presented. For example:

    -0.234
    Integers start with a 1 through 9. Please enter an integer to wordify (q to quit):

---

## Wordify requirements

Here are the given requirements for the Wordify program.

1. Only the very first letter is ever capitalized.
2. Hyphens and commas are unnecessary.
3. There is only one "and" and it comes before the last grouping of numbers below 100. e.g. 100 and 47, but not 1000 nor 100.
4. Support negative numbers: -1000 becomes "Negative one thousand".
5. REPL support is encouraged
6. Include a standalone jar (see `lib/` folder)
7. Include unit tests (see `src/test/`)
8. At a minimum, handle anything that would fit inside a 32-bit signed integer.
9. Handle any error conditions gracefully.
10. The inputs are strictly integers, no fractional values.
11. Echo to `System.out`

## Getting help

Looking for help? Contact the author at [ericdraken@gmail.com](mailto:ericdraken@gmail.com).