1. Only the very first letter is ever capitalized.
2. Hyphens and commas are unnecessary.
3. There is only one "and" and it comes before the last grouping of numbers below 100. e.g. 1000 and 47, but not 1000 and 100.
4. Support negative numbers: -1000 becomes "Negative one thousand".
5. Command line input is fine, but if you could have a REPL-like mechanism, that would be better. 
6. Standalone jar
7. Definitely include unit tests.
8. At a minimum, I'd say that you should be able to handle anything that would fit inside a 32-bit signed integer.
9. Handle any error conditions gracefully. Related to the answer for #5, if you're doing a REPL, you'll want to inform the user that there was a problem and then prompt for a new value.
10. The inputs are strictly integers, no fractional values.
11. Definitely no exceptions / stack traces. Same as #8a, gracefully handle the error, then prompt the user for a proper value.
12. It is not necessary to support multiple inputs on the same line. 
13. Echo to System.out