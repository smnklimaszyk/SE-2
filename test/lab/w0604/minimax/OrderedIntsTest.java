package lab.w0604.minimax;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-06
 */
public class OrderedIntsTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    @Test public void same() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 1);

        // act
        // assert
        assertEquals(1, sut.min());
        assertEquals(1, sut.max());
    }

    @Test(expected = IllegalArgumentException.class) public void bomb() {
        // arrange
        // act
        new OrderedInts(1, 0);

        // assert
    }


    @Test public void aroundSelf() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 2);

        // act
        OrderedInts have = sut.around(sut);

        // assert
        assertEquals(sut, have);
    }

    @Test public void aroundInside() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 4);
        OrderedInts that = new OrderedInts(2, 3);

        // act
        OrderedInts have = sut.around(that);

        // assert
        assertEquals(sut, have);
    }

    @Test public void aroundOutside() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 4);
        OrderedInts that = new OrderedInts(2, 3);

        // act
        OrderedInts have = sut.around(that);

        // assert
        assertEquals(sut, have);
    }

    @Test public void aroundOverlapping() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 4);
        OrderedInts that = new OrderedInts(2, 5);
        OrderedInts want = new OrderedInts(1, 5);

        // act
        OrderedInts have = sut.around(that);

        // assert
        assertEquals(want, have);
    }

    @Test public void aroundTouching() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 3);
        OrderedInts that = new OrderedInts(3, 5);
        OrderedInts want = new OrderedInts(1, 5);

        // act
        OrderedInts have = sut.around(that);

        // assert
        assertEquals(want, have);
    }

    @Test public void aroundDisjoint() {
        // arrange
        OrderedInts sut = new OrderedInts(1, 3);
        OrderedInts that = new OrderedInts(5, 7);
        OrderedInts want = new OrderedInts(1, 7);

        // act
        OrderedInts have = sut.around(that);

        // assert
        assertEquals(want, have);
    }

}
