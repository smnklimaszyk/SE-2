package lab.w0625.decorator;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-24
 */
public class StringImageTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    @Test(expected = IllegalArgumentException.class) public void empty() {
        // arrange
        // act
        new StringImage("");
        // assert
    }

    @Test public void x() {
        // arrange
        TextPicture sut = new StringImage("x");

        // act
        // assert
        assertEquals(1, sut.columns());
        assertEquals(1, sut.lines());
        assertEquals("x", sut.toText().trim());
    }

    @Test public void some() {
        // arrange
        final String lines = """
                                 123
                                 456""";
        TextPicture sut = new StringImage(lines);

        // act
        // assert
        assertEquals(3, sut.columns());
        assertEquals(2, sut.lines());
        assertEquals(lines, sut.toText().trim());
    }

    @Test public void ragged() {
        // arrange
        final String lines = """
                                 123
                                 4""";
        TextPicture sut = new StringImage(lines);

        // act
        // assert
        assertEquals(3, sut.columns());
        assertEquals(2, sut.lines());
        assertEquals("""
                     123
                     4..""", sut.toText().trim());
    }
}
