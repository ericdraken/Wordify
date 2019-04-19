/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview.utils;

import org.jetbrains.annotations.NotNull;

public class ValidateNumeric
{
	final static String EMPTY = "The number is empty.";

	final static String DASH = "There can only be one '-' at the beginning of the number.";

	final static String WHITESPACE = "A valid number doesn't contain whitespace";

	final static String HEX = "Numbers starting with 0 are hex numbers.";

	final static String FRACTION = "Fractional numbers are not supported.";

	final static String MIXED = "Numbers can only contain the numerals 0-9 and start with a -.";

	final static String INVALID = "The number is invalid.";

	public static String validateWithHints( @NotNull String number )
	{
		if ( ! ValidateNumeric.isValidIntegerRepresentation( number ) )
		{
			// Remove leading dash
			number = number.startsWith( "-" ) ? number.substring( 1 ) : number;

			if ( number.isEmpty() )
			{
				return EMPTY;
			}

			if ( number.contains( " " ) )
			{
				return WHITESPACE;
			}

			if ( number.contains( "-" ) )
			{
				return DASH;
			}

			if ( number.startsWith( "0" ) )
			{
				return HEX;
			}

			if ( number.contains( "." ) )
			{
				return FRACTION;
			}

			if ( ! number.matches("^[0-9]+$" ) )
			{
				return MIXED;
			}

			return INVALID;
		}

		return null;
	}

	/**
	 * Test that the string number representation is an integer of any length,
	 * both positive and negative.
	 *
	 * @param number The string representation of an integer number
	 * @return True if this is valid, false otherwise
	 */
	public static boolean isValidIntegerRepresentation( @NotNull String number )
	{
		// Remove the leading negative before performing the digit tests
		number = number.startsWith( "-" ) ? number.substring( 1 ) : number;

		// Empty
		if ( number.isEmpty() )
		{
			return false;
		}

		// Leading zeros means hex
		if ( number.startsWith( "0" ) )
		{
			return false;
		}

		return number.chars().allMatch( Character::isDigit );
	}
}
