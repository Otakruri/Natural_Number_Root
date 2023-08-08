import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method. i.e, creating methods that use
 * internal halving to various numbers (30 numbers) given specific roots (30
 * roots) and checking for the result (already given)...
 *
 * @author Omar Takruri
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is not null";
        assert r >= 2 : "Violation of: r >= 2";

        /*
         * int low = 0; int high = n; int result = high;
         */
        NaturalNumber low = new NaturalNumber2(0);
        NaturalNumber high = new NaturalNumber2(n);
        NaturalNumber result = new NaturalNumber2(high);

        /*
         * Initializing the other variables:
         */
        NaturalNumber mid = new NaturalNumber2();
        NaturalNumber power = new NaturalNumber2();
        NaturalNumber two = new NaturalNumber2(2);

        boolean foundExactRoot = false; //Introducing a boolean to exit loop.

        /*
         * As long as low is less that or = to high. As in the range of space we
         * are using to compare values is not too small to not be able to
         * continue. && The boolean value is not true...
         */
        while (low.compareTo(high) <= 0 && !foundExactRoot) {

            //mid = (low + high) /2
            mid.copyFrom(low);
            mid.add(high);
            mid.divide(two);

            //power = power(mid, r);
            power.copyFrom(mid);
            power.power(r);

            /*
             * A variable (comparing) that hold the three possiblities of our
             * current result. If the value of power is equal to n (0 in natural
             * number terms), then we found the solution. If it's less than n
             * (-1 in natural number terms), then our solution is in the lower
             * bound. Otherwise (in the upper bond), update accordingly.
             */
            int compare = power.compareTo(n);

            if (compare == 0) {
                n.copyFrom(mid); // Found the exact root
                foundExactRoot = true; //Update this value to exit loop.

            } else if (compare < 0) {
                result.copyFrom(mid); // Update the result to the potential root
                low.copyFrom(mid);
                low.increment();
            } else {
                high.copyFrom(mid);
                high.decrement();
            }

        }
        if (!foundExactRoot) {
            n.copyFrom(result); // Update n, to the result value to return.
        }
        /*
         * The key difference is that we don't return an int value here, we
         * rather update the value of "n", in (root(n, root[i]). Therefore,
         * after we find the correct root/answer/result, wee need to exit that
         * loop and update the value of the updated, which in this case is "n".
         */
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
