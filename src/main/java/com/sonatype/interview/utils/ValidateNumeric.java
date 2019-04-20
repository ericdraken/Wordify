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

	final static String ZERO = "Integers start with a 1 through 9.";

	final static String FRACTION = "Fractional numbers are not supported.";

	final static String ASCII = "Only ASCII characters are allowed.";

	final static String MIXED = "Numbers can only contain the numerals 0-9 and start with a -.";

	final static String INVALID = "The number is invalid.";

	final static String NEGATIVE_ZERO = "Zero is a non-negative number and cannot be made negative.";

	final static String NEGATIVE_WHAT = "Negative what?";

	/**
	 *
	 * @param number
	 * @return
	 */
	public static String validateWithHints( @NotNull String number )
	{
		if ( ! ValidateNumeric.isValidIntegerRepresentation( number ) )
		{
			boolean isNegative = number.startsWith( "-" );

			// Remove leading dash
			number = isNegative ? number.substring( 1 ) : number;

			if ( number.isEmpty() )
			{
				if ( isNegative )
					return NEGATIVE_WHAT;
				else
					return EMPTY;
			}

			// Hint things like -0, 01, 0.12, etc.
			if ( number.charAt( 0 ) == '0' )
			{
				if ( isNegative )
				{
					return NEGATIVE_ZERO;
				}
				else if ( number.length() > 1 )
				{
					return ZERO;
				}
			}

			// Simple for-loop over the characters
			// to find the offending character
			for ( char chr : number.toCharArray() )
			{
				if ( chr == '-' )
				{
					return DASH;
				}
				if ( chr == '.' )
				{
					return FRACTION;
				}
				if ( chr >= 128 )
				{
					return ASCII;
				}
				if ( chr == ' ' )
				{
					return WHITESPACE;
				}
				// ASCII '0' = 48, '9' = 57
				if ( chr < 48 || chr > 57 )
				{
					return MIXED;
				}
			}

			// Catch all
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
		boolean isNegative = number.startsWith( "-" );
		number = isNegative ? number.substring( 1 ) : number;

		// Empty
		if ( number.isEmpty() )
		{
			return false;
		}

		if ( number.charAt( 0 ) == '0'  )
		{
			// Leading zeros means hex (or a fraction - either way, no good),
			// and -0 is illogical
			if ( number.length() > 1 || isNegative )
			{
				return false;
			}
		}

		// ASCII '0' = 48, '9' = 57
		return number.chars().allMatch( chr ->
			chr >= 48 && chr <= 57
		);
	}
}
