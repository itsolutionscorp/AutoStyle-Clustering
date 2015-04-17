import list.EquationList;

public class Calculator {
    public EquationList history = new EquationList(null, 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int counter = 0; // to increment across the bits
        int carry = 0; // the value of the carry during adding
        int result = 0; // the result of adding two bits together
        int sum = 0; // the running total of all the results. Also the return value
        int a;
        int b;
        while (counter < 32) {
            a = getBitAt(x, counter);
            b = getBitAt(y, counter);
            result = a ^ b;
            result = result ^ carry; // accounts for any carrying 1's above
            int prevCarry = carry;
            if ((a == 1 && b == 1) || (a == 1 && prevCarry == 1) || (b == 1 && prevCarry == 1)) {
                carry = 1;
            }
            else
                carry = 0;
            if (result == 1)
                sum = setBit(sum, counter);
            counter += 1;
        }        
        return sum;
    }

    // Returns the value of the ith bit of a number
    private int getBitAt(int x, int i) {
        int mask = 1 << i;
        x = x & mask;
        return x >>> i;
    }

    // Sets the ith bit of a number to 1
    private int setBit(int x, int i) {
        int mask = 1 << i;
        return x | mask;
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
        int counter = 0;
        int sum = 0;
        while (counter < 32) {
            if (getBitAt(y, counter) == 1) {
                sum = add(sum, (x << counter));
            }
            counter += 1;
        }
        return sum;
    }

    private int getBit(int x, int i) {
        int mask = 1 << i;
        return x & mask;
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
        history.equation = equation;
        history.result = result;
        history = new EquationList(null, 0, history);
        
    }


    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (history.next != null) {
            EquationList pointer = history;
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
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
        for (int i = 0; i < n; i += 1) {
            EquationList pointer = history;
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
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
        history = new EquationList(null, 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        while (history.next != null) {
            EquationList pointer = history;
            sum += pointer.result;
            pointer = pointer.next;
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
        int product = 0;
        while (history.next != null) {
            EquationList pointer = history;
            product *= pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}