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
public class RotatedTest {
    public static final int KILO = 1_000;

    @Rule public Timeout globalTimeout = Timeout.millis(1_000);


    @Test public void one() {
        // arrange
        TextPicture picture = new StringImage("1");
        TextPicture sut = new Rotated(picture);
        final String want = "1";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void six() {
        // arrange
        final String text = """
                            12
                            34""";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Rotated(picture);
        final String want = """
                            31
                            42""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void tee() {
        // arrange
        final String text = """
                            xxxxx
                            x.x.x
                            ..x
                            .xxx""";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Rotated(picture);
        final String want = """
                            ...xx
                            x...x
                            xxxxx
                            x...x
                            ...xx""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void twice() {
        // arrange
        final String text = "12\n34";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Rotated(new Rotated(picture));
        final String want = """
                            43
                            21""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void looped() {
        // arrange
        final String text = "12\n34";
        TextPicture picture = new StringImage(text);
        int times = 32;
        TextPicture sut = picture;

        // act
        while(times-- > 0)
            sut = new Rotated(sut);

        // assert
        assertTrue(picture.isEqual(sut));
    }

    @Test public void oneLine() {
        // arrange
        final String text = "12";
        final String want = "1\n2";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Rotated(picture);

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void large() {
        // arrange
        TextPicture picture = new Modern('1', KILO, KILO);
        TextPicture sut = new Rotated(new Rotated(picture));

        // act
        // assert
        assertTrue(sut.isEqual(picture));
        assertTrue(picture.isEqual(sut));
    }


    @Ignore @Test public void pleaseIgnore() {
        // arrange
        // act
        // assert
        fail("not implemented");
    }
}
