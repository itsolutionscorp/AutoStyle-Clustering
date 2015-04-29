// Alankrita Dayal (CS61B)

// Alankrita Dayal (CS61B)

import list.EquationList;

public class Calculator {

    public EquationList savedHistory; 

    // YOU MAY WISH TO ADD SOME FIELDS

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
        //return -1;
        int a = x ^ y, b = x & y, sum = a; 
        for (int i = 0; i < 32; i++) {
            b = b << 1;
            sum = b ^ a;
            b = a & b;
            a = sum; 
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
        // return -1;
        int test = (x & 1); 
        int final_solution = 0;
        while (y != 0) {
            if(test != 0) {
                final_solution = add(final_solution, y);
            }
            x = x >> 1;
            y = y << 1;
            test = (x & 1); 
        }
        return final_solution; 
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
        EquationList newHistory = new EquationList(equation, result, savedHistory);
        // or .next = 
        savedHistory = newHistory; 
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

        printHistory(2147483647);

        // or
        
        //EquationList printHistory  = savedHistory; 
        //while (printHistory != null) {
          //  System.out.println(printHistory.equation + " = " + printHistory.result);
            //printHistory = printHistory.next; 
        //}
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
        EquationList printHistory = savedHistory; 
        while (printHistory != null && n > 0) {
            System.out.println(printHistory.equation + " = " + printHistory.result);
            printHistory = printHistory.next;
            n = add(n, -1);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (savedHistory != null) {
            savedHistory = savedHistory.next; 
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        savedHistory = null; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        // return -1;
        EquationList iterateHistory  = savedHistory; 
        int cumsum = 0; 
        while (iterateHistory != null) {
            cumsum = add(cumsum, iterateHistory.result); 
            iterateHistory = iterateHistory.next; 
        }
        return cumsum; 
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        // return -1;
        EquationList iterateHistory  = savedHistory; 
        int cumproduct = 1; 
        while (iterateHistory != null) {
            cumproduct = multiply(cumproduct, iterateHistory.result); 
            iterateHistory = iterateHistory.next; 
        }
        return cumproduct; 
    }
}