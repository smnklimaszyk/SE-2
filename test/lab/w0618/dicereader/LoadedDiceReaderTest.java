package lab.w0618.dicereader;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author R. Schiedermeier
 * @version 2024-06-17
 */
public class LoadedDiceReaderTest extends DiceReaderTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    @Override DiceReader sut() {
        return new LoadedDiceReader(1, 2);
    }

    @Test(expected = IllegalArgumentException.class) public void noRolls() {
        // arrange
        new LoadedDiceReader();
    }

    @Test(expected = IllegalArgumentException.class) public void invalidRolls() {
        // arrange
        new LoadedDiceReader(7);
    }


    @Test public void attack() throws IOException {
        // arrange
        int[] array = new int[] {1};
        try(Reader sut = new LoadedDiceReader(array)) {
            array[0] = 0;
            assertEquals('1', sut.read());
        }
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
        final Map<Integer, List<Integer>> collected = IntStream.range(0, length)
                .map(index -> buffer[index])
                .boxed()
                .collect(Collectors.groupingBy(Function.identity()));
        assertEquals(2, collected.size());
        collected
                .forEach((key, value) -> assertTrue(key >= '1'
                                                            && key <= '2'
                                                            && value.size() == length/2));
    }


    @Ignore @Test public void pleaseIgnoreThisTest() {
        assertEquals("", "");
        assertNull(null);
    }
}
