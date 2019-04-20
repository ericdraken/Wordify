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
			"-123",
			"0",
			"123",
			"89038450983409580934850934850834",
			ValidateNumeric.maxIntegerRepresentation()	// 9999...99
		};
	}

	private static String[][] invalidStringsWithHints()
	{
		// The hints below are strings from ValidateNumeric
		return new String[][]{
			{"", EMPTY},
			{" ", WHITESPACE},
			{INVISIBLE_WHITESPACE, ASCII}, // Invisible space is not considered a space by Java
			{"abc", MIXED},
			{"100c", MIXED},
			{"--100", DASH},
			{"100-000", DASH},
			{"100-", DASH},
			{"07", ZERO},
			{"007", ZERO},
			{"1.0", FRACTION},
			{"123E234", MIXED},
			{"-0", NEGATIVE_ZERO},
			{"-", NEGATIVE_WHAT},
			{"- ", WHITESPACE},
			{"- 123", WHITESPACE},
			{" -123", WHITESPACE},
			{".", FRACTION},
			{"१२३४५६७८९", ASCII}, // Indian numerals are considered digits by Character.isDigit()!
			{"一二", ASCII},
			{ValidateNumeric.maxIntegerRepresentation()+'9', TOO_LONG},
			{"-"+ValidateNumeric.maxIntegerRepresentation()+'9', TOO_LONG}
		};
	}

	@ParameterizedTest
	@MethodSource( value = "validStrings" )
	void isValidIntegerRepresentation_valid( String number )
	{
		assertTrue( ValidateNumeric.isValidIntegerRepresentation( number ) );
	}

	@ParameterizedTest
	@MethodSource( value = "invalidStringsWithHints" )
	void isValidIntegerRepresentation_invalid( String number, String ignored )	// Ignore the hint
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