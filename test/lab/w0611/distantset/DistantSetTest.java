package lab.w0611.distantset;

import lab.w0611.differentchars.DifferentChars;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author R. Schiedermeier
 * @version 2024-06-10
 */
public class DistantSetTest {
    public static final int LOTS = 10_000;

    @Rule public Timeout globalTimeout = Timeout.millis(LOTS);

    @Test public void any() {
        // arrange
        DistantSet<Integer> sut = new DistantSet<>();
        // act
        sut.add(1);
        sut.add(2);
        sut.add(0);
        Set<Integer> want = Set.of(0, 1, 2);
        // assert
        assertEquals(want, sut);
        assertEquals(3, sut.size());
    }

    @Test public void iteratorMin2() {
        // arrange
        DistantSet<Integer> sut = new DistantSet<>((a, b) -> Math.abs(a - b) > 1);
        Set<Integer> want = Set.of(1, 4);
        // act
        sut.add(1);
        sut.add(2);
        sut.add(4);
        sut.add(3);
        // assert
        assertEquals(want, sut);
        assertEquals(2, sut.size());
    }

    @Test public void noCommonChars() {
        // arrange
        DistantSet<String> sut = new DistantSet<>(DifferentChars.differentChars);
        Set<String> want = Set.of("", "ab", "cd", "xxx");
        // act
        sut.add("ab");
        sut.add("a");
        sut.add("cd");
        sut.add("xxx");
        sut.add("xxy");
        sut.add("");
        // assert
        assertEquals(want, sut);
    }

    @Test public void just1() {
        // arrange
        DistantSet<String> sut = new DistantSet<>((_, _) -> false);
        Set<String> want = Set.of("ab");
        // act
        sut.add("ab");
        sut.add("a");
        sut.add("cd");
        sut.add("xxx");
        sut.add("xxy");
        sut.add("");
        // assert
        assertEquals(want, sut);
        assertEquals(1, sut.size());
    }

    @Test public void addAll() {
        // arrange
        DistantSet<String> sut = new DistantSet<>(DifferentChars.differentChars);
        Set<String> want = Set.of("", "ab", "cd", "xxx");
        // act
        boolean have = sut.addAll(List.of("", "ab", "cd", "xxx", "", "ab", "cd", "xxx"));
        System.out.println(sut);
        // assert
        assertTrue(have);
        assertEquals(want, sut);
    }

    @Test public void addAllAgain() {
        // arrange
        DistantSet<String> sut = new DistantSet<>(DifferentChars.differentChars);
        Set<String> want = Set.of("", "ab", "cd", "xxx");
        // act
        sut.addAll(List.of("", "ab", "cd", "xxx"));
        boolean have = sut.addAll(List.of("", "ab", "cd", "xx"));
        // assert
        assertFalse(have);
        assertEquals(want, sut);
    }

    @Test public void addAll1New() {
        // arrange
        DistantSet<String> sut = new DistantSet<>(DifferentChars.differentChars);
        Set<String> want = Set.of("", "ab", "cd", "xxx", "zz");
        // act
        sut.addAll(List.of("", "ab", "cd", "xxx"));
        boolean have = sut.addAll(List.of("", "ab", "cd", "zz"));
        // assert
        assertTrue(have);
        assertEquals(want, sut);
    }

    @Test public void addLots() {
        // arrange
        DistantSet<String> sut = new DistantSet<>(DifferentChars.differentChars);
        Set<String> want = Set.of("ab", "cd");
        // act
        sut.addAll(Stream.generate(() -> "ab").limit(LOTS).toList());
        boolean have = sut.addAll(Stream.generate(() -> "cd").limit(LOTS).toList());
        // assert
        assertTrue(have);
        assertEquals(want, sut);
    }

    @Test public void funnyAdd() {
        // arrange
        DistantSet<Integer> sut = new DistantSet<>();
        Set<Integer> want = Set.of(1, 2);
        // act
        boolean have = sut.addAll(Stream.concat(Stream.generate(() -> 1).limit(LOTS),
                                                Stream.of(2))
                                          .toList());
        // assert
        assertTrue(have);
        assertEquals(want, sut);
    }

    @Test public void bulkAddNone() {
        // arrange
        DistantSet<Integer> sut = new DistantSet<>();
        Set<Integer> want = Set.of(1);
        // act
        sut.add(1);
        boolean have = sut.addAll(Stream.generate(() -> 1).limit(LOTS).toList());
        // assert
        assertFalse(have);
        assertEquals(want, sut);
    }
}
