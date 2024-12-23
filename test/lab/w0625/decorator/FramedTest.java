package lab.w0625.decorator;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-25
 */
public class FramedTest {
    public static final int MEGA = 1_000_000;
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);


    @Test public void one() {
        // arrange
        TextPicture picture = new StringImage("1");
        TextPicture sut = new Framed(picture);
        final String want = """
                            +-+
                            |1|
                            +-+""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void six() {
        // arrange
        final String text = "12\n34";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Framed(picture);
        final String want = """
                            +--+
                            |12|
                            |34|
                            +--+""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void twice() {
        // arrange
        final String text = "12\n34";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Framed(new Framed(picture));
        final String want = """
                            +----+
                            |+--+|
                            ||12||
                            ||34||
                            |+--+|
                            +----+""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void oneLine() {
        // arrange
        final String text = "12";
        final String want = """
                            +--+
                            |12|
                            +--+""";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Framed(picture);

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void large() {
        // arrange
        TextPicture picture = new Modern('1', MEGA, MEGA);
        TextPicture sut = new Framed(picture);

        // act
        // assert
        assertEquals('+', sut.charAt(MEGA + 1, MEGA + 1));
        assertEquals('1', sut.charAt(MEGA, MEGA));
        assertEquals('-', sut.charAt(MEGA + 1, MEGA));
        assertEquals('|', sut.charAt(MEGA, MEGA + 1));
    }

    @Ignore @Test public void pleaseIgnore() {
        // arrange
        // act
        // assert
        fail("not implemented");
    }
}
