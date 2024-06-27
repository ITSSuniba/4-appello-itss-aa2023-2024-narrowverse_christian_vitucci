package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsSamplesTest {
    @Test
    void substringWithNullInput() {
        assertNull(StringUtilsSamples.substring(null, 0, 13));
    }

    @Test
    void substringWithEmptyInput() {
        assertEquals("", StringUtilsSamples.substring("", 0, 13));
    }

    @Test
    void substringWithSpaceInput() {
        assertEquals(" ", StringUtilsSamples.substring(" ", 0, 13));
    }

    @Test
    void substringNegativeStart() {
        assertEquals("ld!", StringUtilsSamples.substring("Hello, World!", -3, 13));
    }

    @Test
    void subStringWithBigNegativeStart() {
        assertEquals("Hello, World!", StringUtilsSamples.substring("Hello, World!", -32518, 32));
    }

    @Test
    void substringNegativeEnd() {
        assertEquals("Hello, W", StringUtilsSamples.substring("Hello, World!", 0, -5));
    }

    @Test
    void subStringWithBigNegativeEnd() {
        assertEquals("", StringUtilsSamples.substring("Hello, World!", -1234, -123));
    }


    @Test
    void substringWithSameStartAndEnd() {
        assertEquals("", StringUtilsSamples.substring("Hello, World!", 6, 6));
    }

    @Test
    void substringWithNegativeStartAndEnd() {
        assertEquals("rld", StringUtilsSamples.substring("Hello, World!", -4, -1));
    }

    @Test
    void substringWithStartGreaterThanLength() {
        assertEquals("", StringUtilsSamples.substring("Hello, World!", 15, 13));
    }

    @Test
    void substringWithEndGreaterThanLength() {
        assertEquals("Hello, World!", StringUtilsSamples.substring("Hello, World!", 0, 19));
    }

    @Test
    void substringWithStartEqualToLength() {
        assertEquals("", StringUtilsSamples.substring("Hello, World!", 13, 7));
    }

    @Test
    void substringWithControlCharacter() {
        assertEquals("World!", StringUtilsSamples.substring("Hello,\tWorld!", 7, 13));
    }

    @Test
    public void testSubstringUnicode() {
        String str = "Hello, \uD83D\uDE03 World!";
        assertEquals("\uD83D\uDE03 World!", StringUtilsSamples.substring(str, 7, str.length()));
}


    //swapCase

    @Test
    void swapCaseNullInput() {
        assertThrows(NullPointerException.class, () -> StringUtilsSamples.swapCase(null));
    }

    @Test
    void swapCaseEmptyInput() {
        assertEquals("", StringUtilsSamples.swapCase(""));
    }

    @Test
    void swapCaseSpaceInput() {
        assertEquals(" ", StringUtilsSamples.swapCase(" "));
    }

    @Test
    void swapCaseInputToUpperCase() {
        assertEquals("ABC", StringUtilsSamples.swapCase("abc"));
    }

    @Test
    void swapCaseInputToLowerCase() {
        assertEquals("abc", StringUtilsSamples.swapCase("ABC"));
    }

    @Test
    void swapCaseInputMixedCase() {
        assertEquals("aBc", StringUtilsSamples.swapCase("AbC"));
    }

    @Test
    void swapCaseInputWithSpacesStartingEndingToUpperCase() {
        assertEquals(" ABC ", StringUtilsSamples.swapCase(" abc "));
    }

    @Test
    void swapCaseInputWithSpacesStartingEndingToLowerCase() {
        assertEquals(" abc ", StringUtilsSamples.swapCase(" ABC "));
    }

    @Test
    void swapCaseInputWithSpacesStartingEndingMixedCase() {
        assertEquals(" aBc ", StringUtilsSamples.swapCase(" AbC "));
    }

    @Test
    void swapCaseInputWithSpacesInBetweenToUpperCase() {
        assertEquals("A B C", StringUtilsSamples.swapCase("a b c"));
    }

    @Test
    void swapCaseInputWithSpacesInBetweenToLowerCase() {
        assertEquals("a b c", StringUtilsSamples.swapCase("A B C"));
    }

    @Test
    void swapCaseInputWithSpacesInBetweenMixedCase() {
        assertEquals("a B c", StringUtilsSamples.swapCase("A b C"));
    }

    @Test
    void swapCaseInputWithAccentCharacter() {
        assertEquals("È", StringUtilsSamples.swapCase("è"));
    }

    @Test
    void swapCaseInputWithNumberCharacter() {
        assertEquals("1", StringUtilsSamples.swapCase("1"));
    }

    @Test
    void swapCaseInputWithNumberCharacterAndAlphabeticalCharacter() {
        assertEquals("2X", StringUtilsSamples.swapCase("2x"));
    }

    @Test
    void swapCaseInputWithSymbols() {
        assertEquals("/", StringUtilsSamples.swapCase("/"));
    }

    @Test
    void swapCaseMixedInput() {
        assertEquals("2X+1", StringUtilsSamples.swapCase("2x+1"));
    }

    @Test
    void swapCaseInputInTitleCase() {
        assertEquals("ǳ", StringUtilsSamples.swapCase("ǲ"));
    }
}