/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.sonatype.interview.utils.Dictionary.*;

public class Wordify
{
	/**
	 * Convert the integer representation of a number to English words
	 * e.g. 1234 -> One thousand two hundred and thirty four
	 *
	 * @param number String representation of an integer
	 * @return English words representing the given number
	 */
    protected static String numberToWords( @NotNull String number )
    {
    	boolean isNegative = false;
		ArrayList<String> parts = new ArrayList<>( 1 );

		// Remove leading dash
		if ( number.charAt( 0 ) == '-' )
		{
			isNegative = true;
			number = number.startsWith( "-" ) ? number.substring( 1 ) : number;
		}

    	int[] groups = groupNonNegativeIntegers( number );

    	// Prepend the word 'negative' to indicate this is a negative number
		if ( isNegative )
		{
			addWords( parts, NEGATIVE );
		}

		// Loop over the groups
    	for ( int i = 0; i < groups.length; i++ )
		{
			if ( i == groups.length - 1 )
			{
				// The one case to return 'zero'
				if ( groups.length == 1 && groups[0] == 0 )
				{
					addWords( parts, ONE_TO_NINETEEN[0] );
				}

				// Possibly and an 'and' if:
				// There are multiple groups
				// There is only one group and it is over 100
				boolean withAnd = groups.length > 1 || groups[0] > 100;
				addWords( parts, smallIntToWords( groups[i], withAnd ) );
			}
			else
			{
				// Skip zero groups except the very last group
				if ( groups[i] != 0 )
				{
					// Most groups
					addWords( parts, smallIntToWords( groups[i] ) );
					addWords( parts, THOUSAND_SCALES[groups.length - 2 - i] );
				}
			}
		}

		// Join the parts
		return String.join( SPACE, parts );
    }

	/**
	 * Helper method to avoid adding empty strings, and also
	 * to capitalize the first word of the resulting phrase
	 *
	 * @param arr Array of strings
	 * @param str String to add to the given array
	 */
	private static void addWords( ArrayList<String> arr, String str )
	{
		if ( !str.isEmpty() )
		{
			if ( arr.size() == 0 )
			{
				// Capitalize the first word only
				str = str.substring( 0, 1 ).toUpperCase() + str.substring( 1 );
			}
			arr.add( str );
		}
	}

	/**
	 * Accept an int from 1 to 999 and return the English words for it
	 * e.g. 45 -> forty five
	 * e.g. 450 -> four hundred fifty
	 *
	 * @param nnNumber An int from 1 to 999
	 * @return The English words for this number. If the number is not in range, then return an empty string.
	 */
	static String smallIntToWords( int nnNumber )
	{
		return smallIntToWords( nnNumber, false );
	}

	/**
	 * Accept an int from 1 to 999 and return the English words for it
	 * e.g. 45 -> forty five
	 * e.g. 450 -> four hundred fifty
	 * If the withAnd parameter is true, then an 'and' is added between the 100 and and remainder
	 * e.g. 450 -> four hundred and fifty
	 *
	 * @param nnNumber An int from 1 to 999
	 * @return The English words for this number. If the number is not in range, then return an empty string.
	 */
	static String smallIntToWords( int nnNumber, boolean withAnd )
	{
		String andStr = ( withAnd ? AND + SPACE : "" );		// add an 'and' between 100 and the remainder

		if ( nnNumber <= 0 || nnNumber >= 1000 )
		{
			return "";
		}
		if ( nnNumber < 20 )
		{
			return andStr + ONE_TO_NINETEEN[nnNumber];
		}
		if ( nnNumber < 100 )
		{
			if ( nnNumber % 10 == 0 )
			{
				return andStr + TENS[nnNumber / 10];
			}
			else
			{
				return andStr + TENS[nnNumber / 10] + SPACE + smallIntToWords( nnNumber % 10, false );
			}
		}
		if ( nnNumber % 100 == 0 )
		{
			return ONE_TO_NINETEEN[nnNumber / 100]
				+ SPACE
				+ HUNDRED;
		}
		else
		{
			return smallIntToWords( (nnNumber / 100) * 100, false )	// Round to nearest hundred
				+ SPACE
				+ smallIntToWords( nnNumber % 100, withAnd );
		}
	}

	/**
	 * Group a non-negative string representation of an integer into
	 * actual integers grouped by thousands
	 *
	 * @param number Non-negative string representation of an integer
	 * @return Array of integers grouped by thousands
	 */
	static int[] groupNonNegativeIntegers( @NotNull String number )
	{
		int size = 3;
		int evenLength = (int) Math.ceil( number.length() / (double) size ) * size;
		int[] groups = new int[evenLength / size];

		String paddedNumber = String.format( "%" + evenLength + "s", number );

		for ( int group = 0; group < groups.length; group++ )
		{
			groups[group] = Integer.parseInt(
				paddedNumber.substring( group * size, (group * size) + size ).trim(),
				10
			);
		}

		return groups;
	}
}
