package lab.w0618.filecat;

import static org.junit.Assert.*;
import org.junit.Before;
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
public class FileCatTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    private final FileCat sut = new FileCat();

    private Path inputFile;

    private Path outputFile;

    @Before public void before() throws IOException {
        inputFile = Files.createTempFile(null, null);
        outputFile = Files.createTempFile(null, null);
        /*
        inputFile = "input";
        outputFile = "output";
         */
    }

    @Test(expected = IOException.class) public void fewArgs() throws IOException {
        // arrange
        new FileCat().concatenate("foo");
    }

    @Test public void empty() throws IOException {
        // arrange

        // act
        sut.concatenate(inputFile.toString(), outputFile.toString());
        final String have = Files.readString(outputFile);

        // assert
        assertEquals("", have);
    }

    @Test public void cat1() throws IOException {
        // arrange
        final String inputString = "ABC";
        Files.writeString(inputFile, inputString);

        // act
        sut.concatenate(inputFile.toString(), outputFile.toString());
        final String have = Files.readString(outputFile);

        // assert
        assertEquals(inputString, have);
    }

    @Test public void catN() throws IOException {
        // arrange
        final String inputString = "ABC";
        final String want = inputString.repeat(5);
        Files.writeString(inputFile, inputString);

        // act
        sut.concatenate(inputFile.toString(), inputFile.toString(), inputFile.toString(), inputFile.toString(), inputFile.toString(), outputFile.toString());
        final String have = Files.readString(outputFile);

        // assert
        assertEquals(want, have);
    }

    @Test public void catLots() throws IOException {
        // arrange
        final String inputString = "ABC";
        final int maxLoops = 20;
        final String[] filenames = {inputFile.toString(), outputFile.toString()};
        Files.writeString(inputFile, inputString);

        // act
        for(int loops = 0; loops < maxLoops; loops++)
            sut.concatenate(filenames[loops%2], filenames[loops%2], filenames[1 - loops%2]);
        final String have = Files.readString(inputFile);

        // assert
        assertEquals(inputString.repeat(1 << maxLoops), have);
    }
}
