package lab.w0611.differentchars;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.function.BiPredicate;

@RunWith(Parameterized.class) public class DifferentCharsTest {
    @Rule public Timeout globalTimeout = Timeout.millis(1_000);

    private final BiPredicate<String, String> sut = DifferentChars::differentChars;

    @Parameters(name = "{1}, {2} = {3}") public static Iterable<Object[]> tests() {
        return Arrays.asList(new Object[][] {
                {"", "", true},
                {"a", "a", false},
                {"a", "aaa", false},
                {"abc", "abc", false},
                {"abc", "cbacba", false},
                {"abc", "", true},
                {"a", "b", true},
                {"a", "bc", true},
                {"ada", "bc", true},
                {"aaa", "bbb", true},
                {"aaa", "bbba", false},
                {"aaac", "bbbc", false},
                {"aaac", "cbbb", false},
                {"abde", "cfcf", true},
                {"abc", "ccbbaa", false},
        });
    }

    @Parameter public String string1;

    @Parameter(1) public String string2;

    @Parameter(2) public boolean want;

    @Test public void testOK() {
        // arrange
        // act
        boolean have12 = sut.test(string1, string2);
        boolean have21 = sut.test(string2, string1);
        // assert
        assertEquals(want, have12);
        assertEquals(want, have21);
    }
}
