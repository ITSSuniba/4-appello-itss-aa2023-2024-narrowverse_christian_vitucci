package org.example;

import net.jqwik.api.*;
import net.jqwik.api.statistics.*;
import net.jqwik.api.arbitraries.IntegerArbitrary;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsSamplePropertyTest {

    //substring function

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void compareSubstringWithinLimits(@ForAll("asciiSourceProvider") String str, @ForAll("intPosRange") int start, @ForAll("intPosRange") int end) {
        Assume.that(str != null);
        Assume.that(start < end);
        String result = StringUtilsSamples.substring(str, start, end);
        if (start < str.length() && end <= str.length()) {
            assertEquals(str.substring(start, end), result);
        }
        for (int i = 0; i < str.length(); i++) {
            Statistics.collect(str.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void compareStringWithStartLessThanZeroWithJavaFunction(@ForAll("asciiSourceProvider") String str, @ForAll("intNegRange") int start, @ForAll("intTotRange") int end) {
        Assume.that(str != null);
        String result = StringUtilsSamples.substring(str, start, end);
        if (start < 0) {
            assertThrows(StringIndexOutOfBoundsException.class, () -> assertEquals(str.substring(start, end), result));
        }
        for (int i = 0; i < str.length(); i++) {
            Statistics.collect(str.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void compareStringWithNegativeEnd(@ForAll("asciiSourceProvider") String str, @ForAll("intPosRange") int start, @ForAll("intNegRange") int end) {
        Assume.that(str != null);
        String result = StringUtilsSamples.substring(str, start, end);
        assertThrows(StringIndexOutOfBoundsException.class, () -> assertEquals(str.substring(start, end), result));
        for (int i = 0; i < str.length(); i++) {
            Statistics.collect(str.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void compareStringWithNegativeIndexes(@ForAll("asciiSourceProvider") String str, @ForAll("intNegRange") int start, @ForAll("intNegRange") int end) {
        Assume.that(str != null);
        String result = StringUtilsSamples.substring(str, start, end);
        assertThrows(StringIndexOutOfBoundsException.class, () -> assertEquals(str.substring(start, end), result));
        for (int i = 0; i < str.length(); i++) {
            Statistics.collect(str.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void substringWithStartGreaterOrEqualThanEnd(@ForAll("asciiSourceProvider") String str, @ForAll("intTotRange") int start, @ForAll("intPosRange") int end) {
        Assume.that(str != null);
        Assume.that(start >= end);
        String result = StringUtilsSamples.substring(str, start, end);
        assertEquals("", result);
        for (int i = 0; i < str.length(); i++) {
            Statistics.collect(str.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void substringWithStartGreaterOrEqualThanStringLength(@ForAll("asciiSourceProvider") String str, @ForAll("intPosRange") int start, @ForAll("intTotRange") int end) {
        Assume.that(str != null);
        String result = StringUtilsSamples.substring(str, start, end);
        if (start > str.length()) {
            assertEquals("", result);
        }
        for (int i = 0; i < str.length(); i++) {
            Statistics.collect(str.charAt(i));
        }
    }

    @Property
    void substringWithNullInput(@ForAll("nullSourceProvider") String string, @ForAll("intTotRange") int start, @ForAll("intTotRange") int end) {
        assertNull(StringUtilsSamples.substring(string, start, end));
    }

    @Property
    void substringWithEmptyStringInput(@ForAll("emptyOrWhitespaceSourceProvider") String string, @ForAll("intPosRange") int start, @ForAll("intPosRange") int end) {
        if (Objects.equals(string, "")) {
            assertEquals("", StringUtilsSamples.substring(string, start, end));
        }
    }

    //swapCase function

    @Property
    void swapCaseWithNullInput(@ForAll("nullSourceProvider") String s) {
        assertThrows(NullPointerException.class, () -> StringUtilsSamples.swapCase(s));
    }

    @Property
    void swapCaseWithEmptyOrWhitespaceStringInput(@ForAll("emptyOrWhitespaceSourceProvider") String s) {
        if (Objects.equals(s, "")) {
            assertEquals("", StringUtilsSamples.swapCase(s));
        }
        if (Objects.equals(s, " ")) {
            assertEquals(" ", StringUtilsSamples.swapCase(s));
        }
    }

    @Property
    @StatisticsReport(format = AlphabeticPercentageStatisticsFormat.class)
    void swapCaseWithAlphabeticLowerCaseStringInput(@ForAll("alphabeticSourceProvider") String s) {
        assertEquals(s.toUpperCase(), StringUtilsSamples.swapCase(s.toLowerCase()));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = AlphabeticPercentageStatisticsFormat.class)
    void swapCaseWithAlphabeticUpperCaseStringInput(@ForAll("alphabeticSourceProvider") String s) {
        assertEquals(s.toLowerCase(), StringUtilsSamples.swapCase(s.toUpperCase()));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = NumericPercentageStatisticsFormat.class)
    void swapCaseWithNumericStringInput(@ForAll("numericSourceProvider") String s) {
        assertEquals(s, StringUtilsSamples.swapCase(s));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = AlphaNumericPercentageStatisticsFormat.class)
    void swapCaseWithAlphaNumericLowerCaseStringInput(@ForAll("alphaNumericSourceProvider") String s) {
        assertEquals(s.toUpperCase(), StringUtilsSamples.swapCase(s.toLowerCase()));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = AlphaNumericPercentageStatisticsFormat.class)
    void swapCaseWithAlphaNumericUpperCaseStringInput(@ForAll("alphaNumericSourceProvider") String s) {
        assertEquals(s.toLowerCase(), StringUtilsSamples.swapCase(s.toUpperCase()));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void swapCaseWithAsciiUpperCaseStringInput(@ForAll("asciiSourceProvider") String s) {
        assertEquals(s.toLowerCase(), StringUtilsSamples.swapCase(s.toUpperCase()));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @StatisticsReport(format = ASCIIPercentageStatisticsFormat.class)
    void swapCaseWithAsciiLowerCaseStringInput(@ForAll("asciiSourceProvider") String s) {
        assertEquals(s.toUpperCase(), StringUtilsSamples.swapCase(s.toLowerCase()));
        for (int i = 0; i < s.length(); i++) {
            Statistics.collect(s.charAt(i));
        }
    }

    @Property
    @Report(Reporting.GENERATED)
    void swapCaseWithTitleCaseStringInput(@ForAll("titleCaseProvider") String s) {
        assertEquals(s.toLowerCase(), StringUtilsSamples.swapCase(s));
    }

    @Provide
    private Arbitrary<String> nullSourceProvider() {
        return Arbitraries.oneOf(
                Arbitraries.strings().injectNull(1)
        );
    }

    @Provide
    private Arbitrary<String> emptyOrWhitespaceSourceProvider() {
        return Arbitraries.strings().whitespace();
    }

    @Provide
    private Arbitrary<String> alphabeticSourceProvider() {
        return Arbitraries.strings().alpha();
    }


    @Provide
    private Arbitrary<String> numericSourceProvider() {
        return Arbitraries.strings().numeric();
    }

    @Provide
    private IntegerArbitrary intPosRange() {
        return Arbitraries.integers().greaterOrEqual(0);
    }

    @Provide
    private IntegerArbitrary intNegRange() {
        return Arbitraries.integers().lessOrEqual(-1);
    }

    @Provide
    private IntegerArbitrary intTotRange() {
        return Arbitraries.integers();
    }

    @Provide
    private Arbitrary<String> alphaNumericSourceProvider() {
        return Arbitraries.strings().alpha().numeric();
    }

    @Provide
    private Arbitrary<String> asciiSourceProvider() {
        return Arbitraries.strings().withCharRange('\u0000', 'ÿ').excludeChars('ß', 'µ').ascii();
    }

    @Provide
    private Arbitrary<String> titleCaseProvider() {
        return Arbitraries.strings().withChars("ǲǈǅǋ");
    }
}

class ASCIIPercentageStatisticsFormat implements StatisticsReportFormat {
    @Override
    public List<String> formatReport(List<StatisticsEntry> entries) {
        List<String> unique = entries.stream().distinct().map(e -> e.name()).toList();
        double size = unique.size();
        return
                Stream.concat(
                        entries
                                .stream()
                                .map(
                                        e ->
                                                String.format("%s: %d (%s)", e.name(), e.count(), e.percentage())
                                ), Stream.of("Percentuale di caratteri ASCII generati: " + ((size / 256) * 100) + "%")).toList();
    }
}

class AlphabeticPercentageStatisticsFormat implements StatisticsReportFormat {
    @Override
    public List<String> formatReport(List<StatisticsEntry> entries) {
        List<String> unique = entries.stream().distinct().map(e -> e.name()).toList();
        double size = unique.size();
        return
                Stream.concat(
                                entries
                                        .stream()
                                        .map(
                                                e ->
                                                        String.format("%s: %d (%s)", e.name(), e.count(), e.percentage())
                                        ), Stream.of(
                                        "Percentuale di caratteri alfabetici generati: " + ((size / 52) * 100) + "%\n" +
                                                "Percentuale di caratteri ASCII generati: " + ((size / 256) * 100) + "%"))
                        .toList();
    }
}

class NumericPercentageStatisticsFormat implements StatisticsReportFormat {
    @Override
    public List<String> formatReport(List<StatisticsEntry> entries) {
        List<String> unique = entries.stream().distinct().map(e -> e.name()).toList();
        double size = unique.size();
        return
                Stream.concat(
                        entries
                                .stream()
                                .map(
                                        e ->
                                                String.format("%s: %d (%s)", e.name(), e.count(), e.percentage())
                                ), Stream.of(
                                "Percentuale di caratteri numerici generati: " + ((size / 10) * 100) + "%\n" +
                                        "Percentuale di caratteri ASCII generati: " + ((size / 256) * 100) + "%"
                        )).toList();
    }
}

class AlphaNumericPercentageStatisticsFormat implements StatisticsReportFormat {
    @Override
    public List<String> formatReport(List<StatisticsEntry> entries) {
        List<String> unique = entries.stream().distinct().map(e -> e.name()).toList();
        double size = unique.size();
        return
                Stream.concat(
                        entries
                                .stream()
                                .map(
                                        e ->
                                                String.format("%s: %d (%s)", e.name(), e.count(), e.percentage())
                                ), Stream.of(
                                "Percentuale di caratteri numerici generati: " + ((size / 62) * 100) + "%\n" +
                                        "Percentuale di caratteri ASCII generati: " + ((size / 256) * 100) + "%"
                        )).toList();
    }
}


