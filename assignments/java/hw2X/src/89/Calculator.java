import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public EquationList history;

    public int add(int x, int y) {
        int carry = 0;
        int answer = 0;
        int i = 0;
        int a = 0;
        int b = 0;
        int c = 0;
        while (i < 32) {
            a = getBit(x, i);
            b = getBit(y, i);
            c = getBit(carry << 1, i);
            if (a == 1 && (b == 1 || c == 1) || (b == 1 && c == 1)) {
                carry = setBit(carry, i);
            }
            if (getBit((carry << 1 ^ (x ^ y)), i) == 1) {
                answer = setBit(answer, i);
            }
            i = i + 1;
        }
        return answer;
    }

    private int getBit(int x, int i) {
        int y = x >> i;
        y = y << 31;
        if (y == -2147483648) {
            return 1;
        }
        return 0;
    }

    private int setBit(int x, int i) {
        int y = 1;
        y = y << i;
        return x ^ y;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        if (x < y) {
            return accumulate(y, x, 0);
        }
        return accumulate(x, y, 0);
    }

    public int accumulate(int x, int y, int n) {
        if (getBit(y, 31) == 1) {
            return accumulate(add(~x, 1), add(~y, 1), n);
        }
        if (y == 1) {
            return add(x, n);
        } else if (y == 0) {
            return n;
        } else {
            return add(n, accumulate(x, y - 2, x << 1));
        }
    }
    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (history == null) {
            return;
        }
        int count = 1;
        EquationList ptr = history;
        while (ptr.next != null) {
            ptr = ptr.next;
            count += 1;
        }
        for (int i = 0; i < count; i++) {
            printHistory(i);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList ptr = history;
        while (n > 0 && ptr.next != null) {
            ptr = ptr.next;
            n -= 1;
        }
        if (n > 0) {
            return;
        }
        System.out.println(ptr.equation + " = " + Integer.toString(ptr.result));
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList ptr = history;
        int sum = 0;
        while (ptr.next != null) {
            sum += ptr.result;
            ptr = ptr.next;
        }
        return sum + ptr.result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList ptr = history;
        int product = 1;
        while (ptr.next != null) {
            product *= ptr.result;
            ptr = ptr.next;
        }
        return product * ptr.result;
    }
}