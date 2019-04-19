/*
 * Copyright (c) 2019. Eric Draken - ericdraken.com
 */

package com.sonatype.interview;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sonatype.interview.utils.Dictionary.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Helper class for parametric testing
class Pair<A, B>
{
	final A fst;
	final B snd;
	Pair( A fst, B snd )
	{
		this.fst = fst;
		this.snd = snd;
	}
}

class WordifyTest
{
	private static String[][] testStrings()
	{
		return new String[][]{
			{"0", "Zero"},
			{"1", "One"},
			{"10", "Ten"},
			{"19", "Nineteen"},
			{"99", "Ninety nine"},
			{"101", "One hundred and one"},
			{"112", "One hundred and twelve"},
			{"199", "One hundred and ninety nine"},
			{"254", "Two hundred and fifty four"},
			{"999", "Nine hundred and ninety nine"},
			{"1001", "One thousand and one"},
			{"1111", "One thousand one hundred and eleven"},
			{"12345", "Twelve thousand three hundred and forty five"},
			{"123456", "One hundred twenty three thousand four hundred and fifty six"},
			{"1000000", "One million"},
			{"100000000", "One hundred million"},
			{"1000000000", "One billion"},
			{"1000000001", "One billion and one"},
			{"-1000000001", "Negative one billion and one"},
			{"-1", "Negative one"},
		};
	}

	private static List<Pair<String, List<Integer>>> numberStringsAndGroups()
	{
		List<Pair<String, List<Integer>>> pairs = new ArrayList<>( 20 );

		pairs.add( new Pair<>( "0", Arrays.asList(0) ) );
		pairs.add( new Pair<>( " 0", Arrays.asList(0) ) );
		pairs.add( new Pair<>( "  0", Arrays.asList(0) ) );

		pairs.add( new Pair<>( "1", Arrays.asList(1) ) );
		pairs.add( new Pair<>( " 1", Arrays.asList(1) ) );
		pairs.add( new Pair<>( "  1", Arrays.asList(1) ) );

		pairs.add( new Pair<>( "12", Arrays.asList(12) ) );
		pairs.add( new Pair<>( " 12", Arrays.asList(12) ) );
		pairs.add( new Pair<>( "123", Arrays.asList(123) ) );

		pairs.add( new Pair<>( "1234", Arrays.asList(1, 234) ) );
		pairs.add( new Pair<>( "12345", Arrays.asList(12, 345) ) );
		pairs.add( new Pair<>( "123456", Arrays.asList(123, 456) ) );
		pairs.add( new Pair<>( "1234567", Arrays.asList(1, 234, 567) ) );

		// This is valid input, but should be filtered as 0x are hex numbers
		// pairs.add( new Pair<>( "0234567", Arrays.asList(0, 234, 567) ) );
		return pairs;
	}

	// 1..99
	private static List<Pair<Integer, String>> smallIntStrings1To99()
	{
		List<Pair<Integer, String>> pairs = new ArrayList<>( 1000 );

		for ( int i = 1; i < 20; i++ )
		{
			pairs.add( new Pair<>( i, ONE_TO_NINETEEN[i] ) );
		}
		for ( int i = 20; i < 100; i += 10 )
		{
			pairs.add( new Pair<>( i, TENS[i / 10] ) );
		}
		for ( int mult = 20; mult < 100; mult += 10 )
		{
			for ( int i = 1; i <= 9; i++ )
			{
				pairs.add( new Pair<>( i+mult, TENS[mult/10] + SPACE + ONE_TO_NINETEEN[i % 10] ) );
			}
		}
		return pairs;
	}

	// 100..900, 101..109, 201..209, etc
	private static List<Pair<Integer, String>> smallIntStringsOver99()
	{
		List<Pair<Integer, String>> pairs = new ArrayList<>( 100 );

		for ( int i = 100; i < 1000; i += 100 )
		{
			pairs.add( new Pair<>( i, ONE_TO_NINETEEN[i / 100] + SPACE + HUNDRED ) );
		}
		for ( int mult = 100; mult < 200; mult += 100 )
		{
			for ( int i = 1; i <= 9; i++ )
			{
				pairs.add( new Pair<>(
					i + mult,
					ONE_TO_NINETEEN[mult / 100]
						+ SPACE
						+ HUNDRED
						+ SPACE
						+ ONE_TO_NINETEEN[i % 10]
				) );
			}
		}
		return pairs;
	}

	@ParameterizedTest
	@MethodSource( value = "numberStringsAndGroups" )
	void groupNonNegativeNumbers( Pair<String, List<Integer>> pair )
	{
		int[] groups = Wordify.groupNonNegativeIntegers( pair.fst );
		int[] testGroups = pair.snd.stream().mapToInt( Integer::intValue ).toArray();
		assertArrayEquals( groups, testGroups );
	}

	@ParameterizedTest
	@MethodSource( value = "smallIntStrings1To99" )
	void smallIntToWords( Pair<Integer, String> pair )
	{
		assertEquals( pair.snd, Wordify.smallIntToWords( pair.fst ) );
	}

	@ParameterizedTest
	@MethodSource( value = "smallIntStringsOver99" )
	void smallIntToWords2( Pair<Integer, String> pair )
	{
		assertEquals( pair.snd, Wordify.smallIntToWords( pair.fst ) );
	}

	@ParameterizedTest
	@MethodSource( value = "testStrings" )
	void smallIntToWords3( String rep, String words )
	{
		assertEquals( words, Wordify.numberToWords( rep ) );
	}
}