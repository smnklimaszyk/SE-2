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
public class MirroredTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    @Test public void one() {
        // arrange
        TextPicture picture = new StringImage("1");
        TextPicture sut = new Mirrored(picture);
        final String want = "1";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void six() {
        // arrange
        TextPicture picture = new StringImage("123\n456\n");
        TextPicture sut = new Mirrored(picture);
        final String want = "321\n654";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void twice() {
        // arrange
        final String text = "123\n456";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Mirrored(new Mirrored(picture));

        // act
        String have = sut.toText();

        // assert
        assertEquals(text, have);
    }

    @Test public void oneLine() {
        // arrange
        final String text = "123";
        final String want = "321";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Mirrored(picture);

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void oneColumn() {
        // arrange
        final String text = "1\n2\n3";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Mirrored(picture);

        // act
        String have = sut.toText();

        // assert
        assertEquals(text, have);
    }

    @Test public void looped() {
        // arrange
        final String text = "123\n456";
        TextPicture picture = new StringImage(text);
        TextPicture sut = picture;
        int loops = 100;
        while(loops-- > 0)
            sut = new Mirrored(sut);

        // act
        String have = sut.toText();

        // assert
        assertEquals(text, have);
    }

    @Test public void large() {
        // arrange
        TextPicture picture = new Modern('1', MAX_VALUE, MAX_VALUE);
        TextPicture sut = new Mirrored(picture);

        // act
        // assert
        assertEquals('1', sut.charAt(MAX_VALUE - 1, MAX_VALUE - 1));
    }

    @Test public void delegate() {
        // arrange
        final char[] array = new char[] {'1'};
        TextPicture sut = new Mirrored(new TextPicture() {
            @Override public int columns() {
                return 1;
            }

            @Override public int lines() {
                return 1;
            }

            @Override public char charAt(int line, int column) {
                return array[0];
            }
        });

        // act
        // assert
        assertEquals("""
                     1""", sut.toText());
        array[0] = '2';
        assertEquals("""
                     2""", sut.toText());
    }

    @Ignore @Test public void pleaseIgnore() {
        // arrange
        // act
        // assert
        fail("not implemented");
    }
}
