import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int getBit(int x, int i) {
            return (x>>i) & 1;
    }
    public int add(int x, int y) {
        int z = 0;
        int i = 0;
        boolean carry = false;
        while (i < 32) {
            if (carry == true && getBit(x, i) == 1 && getBit(y, i) == 1) {
                z = z ^ (1<<i);
                carry = true;
            }
            else if (carry == true && getBit(x, i) == 1 && getBit(y, i) == 0) {
                carry = true;
            }
            else if (carry == true && getBit(x, i) == 0 && getBit(y, i) == 1) {
                carry = true;
            }
            else if (carry == true && getBit(x, i) == 0 && getBit(y, i) == 0) {
                z = z ^ (1<<i);
                carry = false;
            }
            else if (carry == false && getBit(x, i) == 1 && getBit(y, i) == 1) {
                carry = true;
            }
            else if (carry == false && getBit(x, i) == 1 && getBit(y, i) == 0) {
                z = z ^ (1<<i);
                carry = false;
            }
            else if (carry == false && getBit(x, i) == 0 && getBit(y, i) == 1) {
                z = z ^ (1<<i);
                carry = false;
            }
            else {
                carry = false;
            }
            i++; 
        }
        return z;
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
        if (y >= 0) {
            int total = 0;
            int inc = 0;
            while (inc < y) {
                total = add(total, x);
                inc++;
            }
            return total;
        }
        else if (x >= 0 && y < 0) {
            return multiply(y, x);
        }
        else {
            return multiply(add(~x, 1), add(~y, 1));
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
        return;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int n = 0;
        EquationList L = history;
        while (L != null) {
            L = L.next;
            n++;
        }
        printHistory(n);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList L = history;
        while (n > 0) {
            System.out.println(L.equation + " = " + L.result);
            L = L.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
        }
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
        EquationList L = history;
        int sum = 0;
        while (L != null) {
            sum += L.result;
            L = L.next;
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
        EquationList L = history;
        int product = 1;
        while (L != null) {
            product *= L.result;
            L = L.next;
        }
        return product;
    }
}