package lab.w0611.lettercount;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-10
 */
public class LetterCountTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    @Test public void abrakadabra() {
        // arrange
        int[] want = {5, 2, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0};
        // act
        int[] have = LetterCount.letterCount("ABRAKADABRA");
        // assert
        assertArrayEquals(want, have);
    }

    @Test public void simsalabim() {
        // arrange
        int[] want = {2, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 1, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0};
        // act
        int[] have = LetterCount.letterCount("""
                                             SIM
                                             S-A-L-A 
                                             BIM""");
        // assert
        assertArrayEquals(want, have);
    }

    @Test public void empty() {
        // arrange
        int[] want = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        // act
        int[] have = LetterCount.letterCount("");
        // assert
        assertArrayEquals(want, have);
    }

    @Test public void lotsX() {
        // arrange
        int[] want = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1_000, 0, 1};
        // act
        int[] have = LetterCount.letterCount("X".repeat(1_000) + 'Z');
        // assert
        assertArrayEquals(want, have);
    }
}
