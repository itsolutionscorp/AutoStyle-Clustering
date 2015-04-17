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

    public static int getBit(int x, int i) {
        int a = 1 << i;
        int ax = x & a;
        int bit = ax >>> i;
        return bit;
    }

    public static int setBit(int x, int i, int change) {
        if (change == 1) {
            x |= (1 << i);
            return x;
        }
        
        else {
            x &= ~(1 << i);
            return x;
        }
    }

    public int add(int x, int y) {
        int k = 0;
        int c = 0;
        while (k < 32) {
            int xBit = getBit(x, k);
            int yBit = getBit(y, k);

            if ((xBit & yBit & c) == 1) {
                c = 1;
            }

            else if ((xBit & yBit) == 1) {
                c = 1;
                x = setBit(x, k, 0);
            }

            else if (((xBit ^ yBit) == 1) & (c == 1)) {
                c = 1;
                x = setBit(x, k, 0);

            }

            else if ((xBit ^ yBit ^ c) == 1) {
                c = 0;
                x = setBit(x, k, 1);
            }

            k += 1;
        }
        return x;
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
        if ((x == 0) | (y == 0)) {
            return 0;
        }
        int xOrig = x;

        if ((x < 0) & (y < 0)) {
            for (int i = -1; i > y; i--) {
                x = add(x, xOrig);
            }
            x = -x;
        }
        
        else if (y < 0) {
            for (int i = -1; i > y; i--) {
                x = add(x, xOrig);
            }
            x = -x;
        }

        else {
            for (int i = 1; i < y; i++) {
            x = add(x, xOrig);
            }
        }

        return x;
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

    public EquationList allEquations = new EquationList(" ", 0, null);

    public void saveEquation(String equation, int result) {
        EquationList p = new EquationList(equation, result, null);
        p.next = allEquations;
        allEquations = p;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/

    public int lengthOfList (EquationList a) {
        int length = 0;
        if (a == null) {
            return 0;
        }
        length = 1 + lengthOfList(a.next);
        return length;
    }

    public void printAllHistory() {
        EquationList p = allEquations;
        int length = lengthOfList(allEquations);

        if (p == null) {
            return;
        }

        int i = 0;
        while (i < length - 1) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
            i += 1;
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
        EquationList p = allEquations;
        int length = lengthOfList(allEquations);

        if (p == null) {
            return;
        }

        else if (n > length - 1) {
            printAllHistory();
            return;
        }

        int i = 0;
        while (i < n) {
            System.out.println(p.equation + " = " + p.result);
            p = p.next;
            i += 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        allEquations = allEquations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        allEquations = new EquationList(" ", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList p = allEquations;
        int length = lengthOfList(allEquations);
        int sum = 0;
        int i = 0;
        while (i < length - 1) {
            sum += p.result;
            p = p.next;
            i += 1;
        }
        return sum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList p = allEquations;
        int length = lengthOfList(allEquations);
        int product = 1;
        int i = 0;
        while (i < length - 1) {
            product *= p.result;
            p = p.next;
            i += 1;
        }
        return product;
    }
}