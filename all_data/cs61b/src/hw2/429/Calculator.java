import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList allHistory = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // YOUR CODE HERE
        //return x + y;
        int i = 0;
        int total = 0;
        int remainder = 0;
        while (i < 32) {
            int flipped = gitBit(x ^ y, i);
            int anded = gitBit(x & y, i);
            int remaindBit = gitBit(remainder, i);
            if (flipped == 1 && remaindBit == 0) {
                total = setBit(total, i);
            }
            if (anded == 0 && remaindBit == 1 && flipped == 0) {
                total = setBit(total, i);
            }
            if (anded == 1 && remaindBit == 1) {
                total = setBit(total, i);
            }
            i = i + 1;
            if (anded == 1 && remaindBit == 1) {
                remainder = setBit(remainder, i);
            }
            if (anded == 1 && remaindBit == 0) {
                remainder = setBit(remainder, i);
            }
            if (flipped == 1 && remaindBit == 1) {
                remainder = setBit(remainder, i);
            }
        }
        return total;
    }

    //Places a 1 on the ith bit of x
    public int setBit(int x, int index) {
        return 1 << index | x;
    }

    //Returns value of ith bit
    public int gitBit(int x, int index) {
        int end = x >>> index;
        return end & 1;
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
        //YOUR CODE HERE
        int index = 0;
        int total = 0;
        while (index < 32) {
            total = add(total, multiplyPowersTwo(x, gitBit(y, index) << index)); 
            index = add(index, 1);
        }
        return total;
    }

    //Multiplication where y is a power of 2
    public int multiplyPowersTwo(int x, int y) {
        int multshift = 0;
        while (y != 1 & y != 0) {
            y = y >>> 1;
            multshift = add(multshift, 1);
        }
        if (y == 0) {
            return 0;
        }
        return x << multshift;
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
        // YOUR CODE HERE
        allHistory = new EquationList(equation, result, allHistory);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList duplicate = allHistory;
        while (duplicate != null) {
            System.out.println(duplicate.equation + " = " + duplicate.result);
            duplicate = duplicate.next;
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
        // YOUR CODE HERE
        EquationList duplicate = allHistory;
        int counter = 0;
        while (duplicate != null & counter < n) {
            System.out.println(duplicate.equation + " = " + duplicate.result);
            duplicate = duplicate.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        allHistory = allHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        allHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList duplicate = allHistory;
        int total = 0;
        while (duplicate != null) {
            total = total + duplicate.result;
            duplicate = duplicate.next;
        }
        return total;       
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList duplicate = allHistory;
        int total = 1;
        while (duplicate != null) {
            total = total * duplicate.result;
            duplicate = duplicate.next;
        }
        return total;
    }
}