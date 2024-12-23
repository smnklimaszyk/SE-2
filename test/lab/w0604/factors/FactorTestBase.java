package lab.w0604.factors;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public abstract class FactorTestBase {



    private final Factors sut = getSut();

    public abstract Factors getSut();

    @Test
    public void none() {
        // arrange
        List<Integer> want = List.of();

        // act
        List<Integer> have = sut.factors(2);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void prime() {
        // arrange
        List<Integer> want = List.of();

        // act
        List<Integer> have = sut.factors(71);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void largePrime() {
        // arrange
        List<Integer> want = List.of();

        // act
        List<Integer> have = sut.factors(Integer.MAX_VALUE);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void primeSquare() {
        // arrange
        List<Integer> want = List.of(7);

        // act
        List<Integer> have = sut.factors(49);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void square() {
        // arrange
        List<Integer> want = List.of(7, 7 * 7, 7 * 7 * 7);

        // act
        List<Integer> have = sut.factors(49 * 49);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void largePrimeBut1() {
        // arrange
        List<Integer> want = List.of(2, 3, 6, 7, 9, 11, 14, 18, 21, 22, 31, 33, 42, 62, 63, 66, 77, 93, 99, 126, 151, 154, 186, 198, 217, 231, 279, 302, 331, 341, 434, 453, 462, 558, 651, 662, 682, 693, 906, 993, 1023, 1057, 1302, 1359, 1386, 1661, 1953, 1986, 2046, 2114, 2317, 2387, 2718, 2979, 3069, 3171, 3322, 3641, 3906, 4634, 4681, 4774, 4983, 5958, 6138, 6342, 6951, 7161, 7282, 9362, 9513, 9966, 10261, 10923, 11627, 13902, 14043, 14322, 14949, 19026, 20522, 20853, 21483, 21846, 23254, 25487, 28086, 29898, 30783, 32767, 32769, 34881, 41706, 42129, 42966, 49981, 50974, 51491, 61566, 65534, 65538, 69762, 71827, 76461, 84258, 92349, 98301, 99962, 102982, 104643, 112871, 143654, 149943, 152922, 154473, 184698, 196602, 209286, 215481, 225742, 229383, 294903, 299886, 308946, 338613, 349867, 360437, 430962, 449829, 458766, 463419, 549791, 589806, 646443, 677226, 699734, 720874, 790097, 899658, 926838, 1015839, 1049601, 1081311, 1099582, 1292886, 1549411, 1580194, 1649373, 2031678, 2099202, 2162622, 2370291, 3098822, 3148803, 3243933, 3298746, 3848537, 4648233, 4740582, 4948119, 6297606, 6487866, 7110873, 7697074, 9296466, 9896238, 10845877, 11545611, 13944699, 14221746, 17043521, 21691754, 23091222, 27889398, 32537631, 34087042, 34636833, 51130563, 65075262, 69273666, 97612893, 102261126, 119304647, 153391689, 195225786, 238609294, 306783378, 357913941, 715827882, 1073741823);

        // act
        List<Integer> have = sut.factors(Integer.MAX_VALUE - 1);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void few() {
        // arrange
        List<Integer> want = List.of(2, 3, 4, 6);

        // act
        List<Integer> have = sut.factors(12);

        // assert
        Assert.assertEquals(want, have);
    }

    @Test
    public void many() {
        // arrange
        List<Integer> want = List.of(2, 3, 4, 6, 8, 9, 12, 18, 24, 27, 36, 54, 72, 108);

        // act
        List<Integer> have = sut.factors(216);

        // assert
        Assert.assertEquals(want, have);
    }

    @Ignore
    @Test
    public void pleaseIgnoreThisTest() {
        Assert.assertEquals("", "");
        Assert.assertNull(List.of(""));
    }
}