import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList currentFirstItem = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int twoPowI = 1;
        int sum = 0;
        int carry = 0;
        for (int i=1; i<=32; i++) { // integers are 32 bits long
            int xi = x & twoPowI; // xi and yi will hold only ith bit position of x and y
            int yi = y & twoPowI;
            sum |= xi ^ yi ^ carry; // if sum of bits in position i is 1, then sum will have 1 in that position
            carry = ((xi & yi) | ((xi ^ yi) & carry)) << 1; // two or three of the 3 inputs are 1
            twoPowI <<= 1; // next higher bit position to be tried next
        }
        return sum;
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
        // YOUR CODE HERE
//        Calculator buggyCalculator = new StaffCalculator();
        int var1 = x, var2 = y, retVal = 0;
        while (var2 != 0)
        {
            if ((var2 & 1) != 0) {
//                retVal = buggyCalculator.add(retVal, var1);
                retVal = add(retVal, var1);
            }
            var1 <<= 1;
            var2 >>>= 1;
        }
        return retVal;
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
        currentFirstItem = new EquationList(equation, result, currentFirstItem);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(Integer.MAX_VALUE);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int numPrinted = 0;
        EquationList pointer = currentFirstItem;
        while (numPrinted < n) {
            if (pointer == null) {
                return;
            }
            else {
                System.out.println(pointer.equation + " = " + pointer.result);
                pointer = pointer.next;
                numPrinted++;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (currentFirstItem != null) {
            currentFirstItem = currentFirstItem.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        EquationList eqn = currentFirstItem;
        while (eqn != null) {
            currentFirstItem = currentFirstItem.next;
            eqn.next = null;
            eqn = currentFirstItem;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int builder = 0;
        EquationList pointer = currentFirstItem;
        while (pointer != null) {
            builder += pointer.result;
            pointer = pointer.next;
        }
        return builder;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int builder = 1;
        EquationList pointer = currentFirstItem;
        while (pointer != null) {
            builder *= pointer.result;
            pointer = pointer.next;
        }
        return builder;
    }
}