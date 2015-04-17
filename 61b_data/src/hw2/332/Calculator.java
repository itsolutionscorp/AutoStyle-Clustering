import list.EquationList;

public class Calculator {
    public EquationList input = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        if (y == 0) {
            return x;
        }
        int carry;
        carry = (x & y) << 1;
        return add((x ^ y), carry);
    }

    // private int getBit(int x, int i) {
    //     return (x >>> i) & (1);
    // }

    // private int setBit(int x, int i) {
    //     return x & (1 << i);
    // }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        int n = findN(y);
        y = add(y, add((~(1 << n)), 1));
        return add(x << n, multiply(x, y));
    	
	/* very slow version without bitwise operators*/
        // int product = 0;
        // while (y != 0) {
        //     product = add(x, product);
        //     y -= 1;
        // }
        // return product;
    }

    /* Finds n in the largest 2^n below x. */
    private int findN(int x) {
        int result = 0;
        for(int i = 0; i < 32; i++) {
            result = x >> i;
            if (result == 1) {
                return i;
            }
        }
        return result;
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
        input = new EquationList(equation, result, input);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList copy = input;
        while (copy != null) {
            System.out.println(copy.equation + " = " + copy.result);
            copy = copy.next;
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
        EquationList copy = input;
        while (n > 0) {
            System.out.println(copy.equation + " = " + copy.result);
            copy = copy.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        input = input.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        input = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList copy = input;
        int sum = 0;
        while (copy != null) {
            sum += copy.result;
            copy = copy.next;
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
        EquationList copy = input;
        int product = 1;
        while (copy != null) {
            product *= copy.result;
            copy = copy.next;
        }
        return product;
    }
}
