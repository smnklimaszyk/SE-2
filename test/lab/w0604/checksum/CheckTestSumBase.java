package lab.w0604.checksum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import w0604.checksum.Checksum;

public abstract class CheckTestSumBase {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1_000);

    abstract Checksum getSut();
    private final Checksum sut = getSut();

    @Test
    public void zero() {
        // arrange
        int want = 0;

        // act
        int have = sut.checksum(0);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void singleDigit() {
        // arrange
        int want = 7;

        // act
        int have = sut.checksum(7);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void some() {
        // arrange
        int want = 12;

        // act
        int have = sut.checksum(123_123);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void max() {
        // arrange
        int want = 9 * 9;

        // act
        int have = sut.checksum(999_999_999);

        // assert
        Assert.assertEquals(want, have);
    }
}