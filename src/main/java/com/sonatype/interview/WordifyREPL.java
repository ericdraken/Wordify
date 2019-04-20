/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview;

import com.sonatype.interview.utils.ValidateNumeric;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.getProperty;

/**
 * Read in an integer representation of a number and
 * print out the English words representing that number.
 * If the input is invalid, print a hint as to why.
 * This will enter in a REPL loop until q or quit is entered.
 * See the readme.md file for more details
 */
public class WordifyREPL extends Wordify
{
	private final static String VERSION = "1.0";

	/**
	 * Enter the REPL loop
	 * @param args Args are ignored
	 */
	public static void main( String[] args )
	{
		System.out.println( welcomeMessage() );
		System.out.println( help() );

		Scanner in = new Scanner( System.in );
		while ( in.hasNextLine() )
		{
			String str = in.nextLine();    // Get the whole line

			// Be nice and at least trim the input
			str = str.trim();

			// Exit the REPL loop
			if ( str.matches( "^(q|quit|x|exit)" ) )
			{
				break;
			}

			// Show the help message
			if ( str.matches( "^(h|help)" ) )
			{
				System.out.println( help() );
				continue;
			}

			// Get the max supported integer representation
			if ( str.matches( "^(max)" ) )
			{
				str = ValidateNumeric.maxIntegerRepresentation();
			}

			// Get the min supported integer representation
			else if ( str.matches( "^(min)" ) )
			{
				str = "-" + ValidateNumeric.maxIntegerRepresentation();
			}

			// Display a hint, or the correct English words
			String hint = ValidateNumeric.validateWithHints( str );
			if ( hint != null )
			{
				System.err.println( String.format( "%s %s", hint, instructions() ) );
			}
			else
			{
				System.out.println();
				System.out.println( numberToWords( str ) );
				System.out.println();
			}
		}
	}

	/**
	 * Return the detailed help for the REPL
	 *
	 * @return The instructions
	 */
	private static String help()
	{
		return
			String.join(
				System.lineSeparator(),
				Arrays.asList(
					"Quit by entering 'q' or 'quit'. See this message again with 'h' or 'help'.",
					"Enter 'max' to see the largest number supported, or 'min' to see the smallest.",
					"Please enter an integer to wordify:"
				)
			);
	}

	/**
	 * Return the instructions for the REPL
	 *
	 * @return The instructions
	 */
	private static String instructions()
	{
		return "Please enter an integer to wordify (q to quit):";
	}

	/**
	 * Return a simple welcome message and Java version
	 *
	 * @return Welcome message
	 */
	private static String welcomeMessage()
	{
		String str = String.format(
			"Welcome to Wordify version %s (Java %s)",
			VERSION,
			getProperty( "java.version" )
		);
		String lines = String.format( "%" + str.length() + "s", "" ).replace( ' ', '-' );
		return lines + System.lineSeparator() + str + System.lineSeparator() + lines;
	}
}
