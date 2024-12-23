package lab.w0618.dicereader;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author R. Schiedermeier
 * @version 2024-06-17
 */
public class DiceReaderTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    DiceReader sut() {
        return new DiceReader();
    }


    @Test public void empty() throws IOException {
        // arrange
        final char[] buffer = new char[0];

        // act
        int have;
        try(Reader sut = sut()) {
            have = sut.read(buffer, 0, 0);
        }

        // assert
        assertEquals(0, have);
    }

    @Test public void none() throws IOException {
        // arrange
        final char[] buffer = {'A'};

        // act
        int have;
        try(Reader sut = sut()) {
            have = sut.read(buffer, 0, 0);
        }

        // assert
        assertEquals(0, have);
        assertArrayEquals(new char[] {'A'}, buffer);
    }

    @Test public void read1() throws IOException {
        // arrange
        final int length = 1_000_000;
        final char[] buffer = new char[length];

        // act
        int have;
        try(Reader sut = sut()) {
            have = sut.read(buffer, 1, 1);
        }


        // assert
        assertEquals(1, have);
        assertEquals(0, buffer[0]);
        assertTrue(buffer[1] >= '1' && buffer[1] <= '6');
        assertEquals(0, buffer[2]);
        assertEquals(0, buffer[length - 1]);
    }

    @Test public void lots() throws IOException {
        // arrange
        final int length = 1_000_000;
        final char[] buffer = new char[length];

        // act
        int have;
        try(Reader sut = sut()) {
            have = sut.read(buffer, 0, length);
        }

        // assert
        assertEquals(length, have);
        IntStream.range(0, length)
                .map(index -> buffer[index])
                .boxed()
                .collect(Collectors.groupingBy(Function.identity()))
                .forEach((key, value) -> assertTrue(key >= '1'
                                                            && key <= '6'
                                                            && value.size() >= length/10));
    }


    @Ignore @Test public void pleaseIgnoreThisTest() {
        assertEquals("", "");
        assertNull(null);
    }
}
