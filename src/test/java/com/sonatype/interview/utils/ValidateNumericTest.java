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
			"."
		};
	}

	private static String[][] invalidStringsWithHints()
	{
		return new String[][]{
			{"", EMPTY},
			{" ", WHITESPACE},
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
			{"123", null}
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