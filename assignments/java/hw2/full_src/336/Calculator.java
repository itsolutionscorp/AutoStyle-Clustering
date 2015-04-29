import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two a`ddends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int sum = 0;
        Boolean carry = false;
        for (int i = 0; i < 32; i += 1) {
            int a = getBit(x, 0);
            int b = getBit(y, 0);
            if (carry == false){
                if (a == 1 && b == 1) {
                    carry = true;
                } else if (a == 1 || b == 1) {
                    sum = setBit(sum, i);
                }
            } else if (carry == true){
                if (a == 1 && b == 1) {
                    sum = setBit(sum, i);
                } else if (a == 0 && b == 0) {
                    sum = setBit(sum, i);
                    carry = false;
                }
            }
            x = x >>> 1;
            y = y >>> 1;
        }
        return sum;
    }

    public int getBit(int x, int i) {
        return (x >> i) & 1;
    }

    public int setBit(int x, int i) {
        return x | (1 << i);
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
        int product = 0;
        for (int n = 0; n < 32; n += 1) {
            int helper = 0;
            int multiplier = x;
            for (int i = 0; i < 32; i += 1) {
                int a = getBit(multiplier, 0);
                int b = getBit(y, 0);
                if (b == 1) {
                    if (a == 1) {
                        helper = setBit(helper, i);
                    }
                    multiplier = multiplier >>> 1;
                }
            }
            product = add(product, helper << n);
            y = y >>> 1;
        }
        return product;
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
        printHistory(2147483647);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList equations = history;
        while (n > 0 && equations != null) {
            System.out.println(equations.equation + " = " + equations.result);
            equations = equations.next;
            n -= 1;
        }
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
        int sum = 0;
        EquationList equations = history;
        while (equations != null) {
            sum = add(sum, equations.result);
            equations = equations.next;
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
        int product = 1;
        EquationList equations = history;
        while (equations != null) {
            product = multiply(product, equations.result);
            equations = equations.next;
        }
        return product;
    }
}