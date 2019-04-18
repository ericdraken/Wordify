/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview.utils;

import org.jetbrains.annotations.NotNull;

public class Numeric
{
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
		if ( number.startsWith( "-" ) )
		{
			number = number.substring( 1 );
		}

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
