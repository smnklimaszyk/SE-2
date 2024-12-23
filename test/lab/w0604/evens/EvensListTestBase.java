package lab.w0604.evens;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.ArrayList;
import java.util.List;

/** Tests Laboraufgabe.
 * @author R. Schiedermeier
 * @version 2024-06-03
 */
public abstract class EvensListTestBase {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    abstract EvensList getSUT();

    @Test public void empty() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of();

        // act
        List<Integer> have = sut.evens(0, 0);

        // assert
        assertEquals(want, have);
    }

    @Test public void none() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of();

        // act
        List<Integer> have = sut.evens(1, 1);

        // assert
        assertEquals(want, have);
    }

    @Test public void strange() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of();

        // act
        List<Integer> have = sut.evens(10, 1);

        // assert
        assertEquals(want, have);
    }

    @Test public void one() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of(2);

        // act
        List<Integer> have = sut.evens(1, 4);

        // assert
        assertEquals(want, have);
    }

    @Test public void justOne() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of(2);

        // act
        List<Integer> have = sut.evens(2, 3);

        // assert
        assertEquals(want, have);
    }

    @Test public void some() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of(2, 4, 6);

        // act
        List<Integer> have = sut.evens(2, 7);

        // assert
        assertEquals(want, have);
    }

    @Test public void someOf() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = List.of(2, 4, 6);

        // act
        List<Integer> have = sut.evens(1, 8);

        // assert
        assertEquals(want, have);
    }

    @Test public void many() {
        // arrange
        EvensList sut = getSUT();
        List<Integer> want = new ArrayList<>();
        final int stopper = 1_000_000;
        for(int number = 0; number < stopper; number += 2)
            want.add(number);

        // act
        List<Integer> have = sut.evens(0, stopper);

        // assert
        assertEquals(want, have);
    }
}
