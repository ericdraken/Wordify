/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.sonatype.interview.utils.ValidateNumeric.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateNumericTest
{
	// Zero-width whitespace characters
	// REF: https://stackoverflow.com/a/21797208/1938889
	private final static String INVISIBLE_WHITESPACE = "123" + Character.toString( (char)8203 ) + "456";

	private static String[] validStrings()
	{
		return new String[]{
			"123",
			"-123",
			"89038450983409580934850934850834",
			"890384509834095809348509348508348903845098340958093485093485083489038450983409580934850934850834890384509834095809348509348508348903845098340958093485093485083489038450983409580934850934850834"
		};
	}

	private static String[] invalidStrings()
	{
		return new String[]{
			"",
			" ",
			INVISIBLE_WHITESPACE,
			"abc",
			"100c",
			"--100",
			"100-000",
			"100-",
			"07",
			"007",
			"1.0",
			"123E234",
			"-0",
			"-",
			"- ",
			"- 123",
			" -123",
			".",
			"१२३४५६७८९",	// Indian numerals are considered digits by Character.isDigit()!
			"一二" 		// Japanese numbers
		};
	}

	private static String[][] invalidStringsWithHints()
	{
		return new String[][]{
			{"", EMPTY},
			{" ", WHITESPACE},
			// {INVISIBLE_WHITESPACE, WHITESPACE},	// Invisible space is not considered a space by Java
			{INVISIBLE_WHITESPACE, ASCII},
			{"abc", MIXED},
			{"100c", MIXED},
			{"--100", DASH},
			{"100-000", DASH},
			{"100-", DASH},
			{"07", HEX},
			{"007", HEX},
			{"1.0", FRACTION},
			{"123E234", MIXED},
			{"-0", HEX},
			{"-", EMPTY},
			{"- ", WHITESPACE},
			{"- 123", WHITESPACE},
			{" -123", WHITESPACE},
			{".", FRACTION},
			{"123", null},
			{"१२३४५६७८९", ASCII}, // Indian numerals are considered digits by Character.isDigit()!
			{"一二", ASCII}
		};
	}

	@ParameterizedTest
	@MethodSource( value = "validStrings" )
	void isValidIntegerRepresentation_valid( String number )
	{
		assertTrue( ValidateNumeric.isValidIntegerRepresentation( number ) );
	}

	@ParameterizedTest
	@MethodSource( value = "invalidStrings" )
	void isValidIntegerRepresentation_invalid( String number )
	{
		assertFalse( ValidateNumeric.isValidIntegerRepresentation( number ) );
	}

	@ParameterizedTest
	@MethodSource( value = "invalidStringsWithHints" )
	void validateWithHints( String number, String hint )
	{
		assertEquals( hint, ValidateNumeric.validateWithHints( number ) );
	}
}