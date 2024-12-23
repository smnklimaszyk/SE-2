package lab.w0604.checksum;

/**
 * @author R. Schiedermeier
 * @version 2024-06-03
 */
public class StreamChecksumTest extends CheckTestSumBase{

    @Override
    Checksum getSut() {
        return new StreamChecksum();
    }
}
