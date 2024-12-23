package lab.w0604.checksum;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author R. Schiedermeier
 * @version 2024-06-03
 */
public class LoopChecksumTest extends CheckTestSumBase{

    @Override
    Checksum getSut() {
        return new LoopChecksum();
    }
}
