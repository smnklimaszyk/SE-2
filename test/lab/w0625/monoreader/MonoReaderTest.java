package lab.w0625.monoreader;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.io.IOException;
import java.io.Reader;

/**
 * @author R. Schiedermeier
 * @version 2024-06-24
 */
public class MonoReaderTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);


    @Test(expected = RuntimeException.class) public void invalid() {
        // arrange
        // act
        new MonoReader('a', -1);
    }

    @Test public void emptyRead() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 0)) {
            int want = -1;
            // act
            int have = sut.read();
            // assert
            assertEquals(want, have);
        }
    }

    @Test public void emptyRead0() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 0)) {
            int want = -1;
            char[] buffer = new char[0];
            // act
            int have = sut.read(buffer);
            // assert
            assertEquals(want, have);
        }
    }

    @Test public void emptyRead1() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 0)) {
            int want = -1;
            char[] buffer = new char[1];
            // act
            int have = sut.read(buffer);
            // assert
            assertEquals(want, have);
        }
    }

    @Test public void oneRead() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 1)) {
            int want = 'x';
            // act
            int have = sut.read();
            // assert
            assertEquals(want, have);
        }
    }

    @Test public void oneRead0() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 1)) {
            int want = 0;
            char[] buffer = new char[0];
            // act
            int have = sut.read(buffer);
            // assert
            assertEquals(want, have);
        }
    }

    @Test public void oneRead0of1() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 1)) {
            int want = 0;
            char[] buffer = new char[1];
            // act
            int have = sut.read(buffer, 1, 0);
            // assert
            assertEquals(want, have);
        }
    }

    @Test public void readSome() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 1)) {
            int want = 'x';
            // act
            // assert
            assertEquals(want, sut.read());
            assertEquals(-1, sut.read());
        }
    }

    @Test public void read1ofMany() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', Integer.MAX_VALUE)) {
            int want = 'x';
            // act
            // assert
            assertEquals(want, sut.read());
        }
    }

    @Test public void readRead21() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 3)) {
            char[] buffer = new char[2];
            // act
            // assert
            assertEquals(2, sut.read(buffer));
            assertArrayEquals(new char[] {'x', 'x'}, buffer);
            buffer = new char[2];
            assertEquals(1, sut.read(buffer));
            assertArrayEquals(new char[] {'x', 0}, buffer);
            assertEquals(-1, sut.read(buffer));
        }
    }

    @Test public void readRead22() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 4)) {
            char[] buffer = new char[2];
            // act
            // assert
            assertEquals(2, sut.read(buffer));
            assertArrayEquals(new char[] {'x', 'x'}, buffer);
            buffer = new char[2];
            assertEquals(2, sut.read(buffer));
            assertArrayEquals(new char[] {'x', 'x'}, buffer);
            assertEquals(-1, sut.read(buffer));
        }
    }

    @Test public void readRead20() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 2)) {
            char[] buffer = new char[4];
            // act
            // assert
            assertEquals(2, sut.read(buffer));
            assertArrayEquals(new char[] {'x', 'x', 0, 0}, buffer);
            assertEquals(-1, sut.read(buffer));
        }
    }

    @Test public void readRead2() throws IOException {
        // arrange
        try(Reader sut = new MonoReader('x', 3)) {
            char[] buffer = new char[4];
            // act
            // assert
            assertEquals(2, sut.read(buffer, 1, 2));
            assertArrayEquals(new char[] {0, 'x', 'x', 0}, buffer);
            assertEquals(0, sut.read(buffer, 4, 0));
        }
    }


    @Ignore @Test public void pleaseIgnore() {
        // arrange
        // act
        // assert
        fail("not implemented");
    }
}
