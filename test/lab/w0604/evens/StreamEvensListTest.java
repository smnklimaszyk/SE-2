package lab.w0604.evens;

/**
 * @author R. Schiedermeier
 * @version 2024-06-03
 */
public class StreamEvensListTest extends EvensListTestBase {
    @Override EvensList getSUT() {
        return new StreamEvensList();
    }
}
