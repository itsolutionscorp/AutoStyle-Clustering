public class XsBeforeOs {

    // Rearrange the elements of values so that all the Xs precede all
    // the Os.
    public static void rearrange(char[] values) {
        int lastXpos = -1;
        char[] temp = new char[values.length];
        int xCount = 0;
        int oCount = values.length / 2;

        for (int k = 0; k < values.length; k++) {
            if (values[k] == 'X') {
                temp[xCount] = 'X';
                xCount += 1;
            } else {
                temp[oCount] = 'O';
                oCount += 1;
            }
            try {
                isOK(values, k);
            } catch (IllegalStateException e) {
                System.err.println("inconsistency at position " + k);
            }
        }
        values = temp;
    }

    // Check for consistency. All the Xs in elements 0 to k of values should
    // precede all the Os. Throw an IllegalStateException if this is not
    // consistent.
    public static void isOK (char[] values, int k) {
        boolean flag = false;

        for (int n = 0; n <= k; n += 1) {
            if (values[n] == 'O') {
                flag = true;
            }
            if (values[n] == 'X' && flag == true) {
                throw new IllegalStateException("Order is wrong.");
            }
        }
    }
}
