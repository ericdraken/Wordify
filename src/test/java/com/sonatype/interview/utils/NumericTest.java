/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumericTest
{
	public static String[] validStrings()
	{
		return new String[]{
			"123",
			"-123",
			"89038450983409580934850934850834",
			"890384509834095809348509348508348903845098340958093485093485083489038450983409580934850934850834890384509834095809348509348508348903845098340958093485093485083489038450983409580934850934850834"
		};
	}

	public static String[] invalidStrings()
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
		};
	}

	@ParameterizedTest
	@MethodSource( value = "validStrings" )
	void isValidIntegerRepresentation_valid( String number )
	{
		assertTrue( Numeric.isValidIntegerRepresentation( number ) );
	}

	@ParameterizedTest
	@MethodSource( value = "invalidStrings" )
	void isValidIntegerRepresentation_invalid( String number )
	{
		assertFalse( Numeric.isValidIntegerRepresentation( number ) );
	}
}