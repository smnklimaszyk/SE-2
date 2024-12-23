package lab.w0604.minimax;

import org.junit.Rule;
import org.junit.rules.Timeout;

/**
 * @author R. Schiedermeier
 * @version 2024-06-03
 */
public class StreamLinesMinmaxLengthsTest extends LinesMinmaxLengthsTestBase {
    @Rule public Timeout globalTimeout = Timeout.millis(30_000);

    @Override LinesMinmaxLengths getSUT() {
        return new StreamLinesMinmaxLengths();
    }
}
