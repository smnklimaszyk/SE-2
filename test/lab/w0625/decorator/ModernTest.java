package lab.w0625.decorator;

import static java.lang.Integer.MAX_VALUE;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-24
 */
public class ModernTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);


    @Test(expected = IllegalArgumentException.class) public void nolines() {
        // arrange
        // act
        new Modern('$', 0, 1);
    }

    @Test(expected = IllegalArgumentException.class) public void noColumns() {
        // arrange
        // act
        new Modern('$', 1, 0);
    }

    @Test public void columns() {
        // arrange
        TextPicture sut = new Modern('$', 1, 1);
        // act
        // assert
        assertEquals(1, sut.lines());
        assertEquals(1, sut.columns());
        assertEquals('$', sut.charAt(0, 0));
    }

    @Test public void monumental() {
        // arrange
        TextPicture sut = new Modern('\0', MAX_VALUE, MAX_VALUE);
        // act
        // assert
        assertEquals(MAX_VALUE, sut.lines());
        assertEquals(MAX_VALUE, sut.columns());
        assertEquals('\0', sut.charAt(0, 0));
        assertEquals('\0', sut.charAt(MAX_VALUE - 1, MAX_VALUE - 1));
    }
    @Ignore @Test public void pleaseIgnore() {
        // arrange
        // act
        // assert
        fail("not implemented");
    }
}
