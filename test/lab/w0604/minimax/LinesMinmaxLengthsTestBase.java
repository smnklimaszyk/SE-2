package lab.w0604.minimax;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-03
 */
public abstract class LinesMinmaxLengthsTestBase {
    @Rule public Timeout globalTimeout = Timeout.millis(30_000);

    private final LinesMinmaxLengths sut = getSUT();

    abstract LinesMinmaxLengths getSUT();

    @Test public void emptyText() {
        // arrange
        String text = "";

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertNull(have);
    }

    @Test public void emptyLines() {
        // arrange
        String text = """
                                            
                      """;

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertNull(have);
    }

    @Test public void oneLine() {
        // arrange
        String text = "foo";
        OrderedInts want = new OrderedInts (3, 3);

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertEquals(want, have);
    }

    @Test public void someLines() {
        // arrange
        String text = """
                      Abra
                      ka
                      dabra
                      Sim
                      sala
                      bim                   
                      """;
        OrderedInts want = new OrderedInts (2, 5);

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertEquals(want, have);
    }

    @Test public void sameLengthLines() {
        // arrange
        String text = """
                      foo
                      bar
                      baz                      
                      """;
        OrderedInts want = new OrderedInts (3, 3);

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertEquals(want, have);
    }

    @Test public void mixed() {
        // arrange
        String text = """
                      Dreimal
                                            
                      \t\fschwarzer
                      Kater\s\s\s\s\s\s\s\s
                                            
                        """;
        OrderedInts want = new OrderedInts (5, 9);

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertEquals(want, have);
    }

    @Test public void longLines() {
        // arrange
        final int million = 1_000_000;
        String text = ("x".repeat(million) + "\n\n").repeat(1_000);
        OrderedInts want = new OrderedInts (million, million);

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertEquals(want, have);
    }

    @Test public void veryEmpty() {
        // arrange
        final int million = 1_000_000;
        String text = (" ".repeat(million) + "\n\n").repeat(1_000);

        // act
        OrderedInts have = sut.analyse(text);

        // assert
        assertNull(have);
    }
}
