package lab.w0625.decorator;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-25
 */
public class TextPictureTest {
    //@Rule public Timeout globalTimeout = Timeout.millis(1_000);

    TextPicture sut = new TextPicture() {
        @Override public int columns() {
            return 3;
        }

        @Override public int lines() {
            return 2;
        }

        @Override public char charAt(int line, int column) {
            return "123456".charAt(3*line + column);
        }
    };

    @Test public void toText() {
        // arrange
        String want = """
                      123
                      456""";
        // act
        String have = sut.toText();
        // assert
        assertEquals(want, have);
    }

    @Test public void printNotATest() {
        sut.print();
    }

    @Test public void reflexive() {
        assertTrue(sut.isEqual(sut));
    }

    @Test public void otherType() {
        assertFalse(sut.isEqual("Foo"));
    }

    @Test public void otherWidth() {
        assertFalse(sut.isEqual(new TextPicture() {
            @Override public int columns() {
                return 2;
            }

            @Override public int lines() {
                return 2;
            }

            @Override public char charAt(int line, int column) {
                return sut.charAt(line, column);
            }
        }));
    }

    @Test public void otherHeight() {
        assertFalse(sut.isEqual(new TextPicture() {
            @Override public int columns() {
                return 3;
            }

            @Override public int lines() {
                return 1;
            }

            @Override public char charAt(int line, int column) {
                return sut.charAt(line, column);
            }
        }));
    }

    @Test public void otherChar() {
        assertFalse(sut.isEqual(new TextPicture() {
            @Override public int columns() {
                return 3;
            }

            @Override public int lines() {
                return 2;
            }

            @Override public char charAt(int line, int column) {
                return line*column == 1? 'x': sut.charAt(line, column);
            }
        }));
    }

    @Test public void notNull() {
        assertFalse(sut.isEqual(null));
    }
}
