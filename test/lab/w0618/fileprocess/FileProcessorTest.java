package lab.w0618.fileprocess;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author R. Schiedermeier
 * @version 2024-06-17
 */
public class FileProcessorTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    @Test public void empty() throws IOException {
        // arrange
        Path tempSource = Files.createTempFile(null, null);
        Path tempDestination = Files.createTempFile(null, null);
        FileProcessor sut = new FileProcessor(n -> n);

        // act
        int have = sut.copy(tempSource.toString(), tempDestination.toString());

        // assert
        assertTrue(Files.exists(tempDestination));
        assertTrue(Files.isRegularFile(tempDestination));
        assertEquals(0, Files.size(tempDestination));
        assertEquals(0, have);
    }

    @Test public void abc() throws IOException {
        // arrange
        Path tempSource = Files.createTempFile(null, null);
        Files.writeString(tempSource, "abc");
        Path tempDestination = Files.createTempFile(null, null);
        FileProcessor sut = new FileProcessor(Character::toUpperCase);
        String wantString = "ABC";
        int want = wantString.length();

        // act
        int have = sut.copy(tempSource.toString(), tempDestination.toString());

        // assert
        assertTrue(Files.exists(tempDestination));
        assertTrue(Files.isRegularFile(tempDestination));
        assertEquals(wantString, Files.readString(tempDestination));
        assertEquals(want, Files.size(tempDestination));
        assertEquals(want, have);
    }

    @Test public void mixed() throws IOException {
        // arrange
        Path tempSource = Files.createTempFile(null, null);
        final String sourceString = "\nA9a\t\0\r";
        Files.writeString(tempSource, sourceString);
        Path tempDestination = Files.createTempFile(null, null);
        FileProcessor sut = new FileProcessor(Character::toUpperCase);
        String wantString = sourceString.toUpperCase();
        int want = wantString.length();

        // act
        int have = sut.copy(tempSource.toString(), tempDestination.toString());

        // assert
        assertTrue(Files.exists(tempDestination));
        assertTrue(Files.isRegularFile(tempDestination));
        assertEquals(wantString, Files.readString(tempDestination));
        assertEquals(want, Files.size(tempDestination));
        assertEquals(want, have);
    }

    @Test public void zero() throws IOException {
        // arrange
        Path tempSource = Files.createTempFile(null, null);
        final String sourceString = "\nA9a\t\0\r";
        Files.writeString(tempSource, sourceString);
        Path tempDestination = Files.createTempFile(null, null);
        FileProcessor sut = new FileProcessor(n -> 0);
        String wantString = "\0".repeat(sourceString.length());
        int want = wantString.length();

        // act
        int have = sut.copy(tempSource.toString(), tempDestination.toString());

        // assert
        assertTrue(Files.exists(tempDestination));
        assertTrue(Files.isRegularFile(tempDestination));
        assertEquals(wantString, Files.readString(tempDestination));
        assertEquals(want, Files.size(tempDestination));
        assertEquals(want, have);
    }
}
