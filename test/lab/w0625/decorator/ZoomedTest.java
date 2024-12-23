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
public class ZoomedTest {
    public static final int MEGA = 1_000_000;
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);


    @Test public void one() {
        // arrange
        TextPicture picture = new StringImage("1");
        TextPicture sut = new Zoomed(picture);
        final String want = "11\n11";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void six() {
        // arrange
        final String text = "12\n34";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Zoomed(picture);
        final String want = "1122\n1122\n3344\n3344";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void twice() {
        // arrange
        final String text = "12\n34";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Zoomed(new Zoomed(picture));
        final String want = """
                            11112222
                            11112222
                            11112222
                            11112222
                            33334444
                            33334444
                            33334444
                            33334444""";

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void oneLine() {
        // arrange
        final String text = "12";
        final String want = "1122\n1122";
        TextPicture picture = new StringImage(text);
        TextPicture sut = new Zoomed(picture);

        // act
        String have = sut.toText();

        // assert
        assertEquals(want, have);
    }

    @Test public void large() {
        // arrange
        TextPicture picture = new Modern('1', MEGA, MEGA);
        TextPicture sut = new Zoomed(new Zoomed(picture));

        // act
        // assert
        assertEquals('1', sut.charAt(4*MEGA - 1, 4*MEGA - 1));
    }

    @Ignore @Test public void pleaseIgnore() {
        // arrange
        // act
        // assert
        fail("not implemented");
    }

}
