import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList;

    //writing getBit function
    // private int getBit(int x, int i) {
    //     //getBit(20, 0) returns rightmost bit (0)
    //     //getBit(20, 2) returns second to right bit (1)

    // }

    // private int setBit(int x, int i) {
    //     //setBit(20, 3) -> 28
    // }


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
        int and;
        while (y != 0) { 
            and = x & y; //determine which ones need to be carried
            x = x ^ y; //XOR to redefine x as the carry
            y = and << 1; //shift (increment) and repeat process  
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
        // YOUR CODE HERE
        //find way to do powers of 2 then multiples of 2 then for odds
        int prod = 0;
        while (x != 0) {
            if ((y & 1) != 0) { //test if y is odd
                prod = add(prod, x); //add x to prod and increment to get even number.
            }
            y = y >> 1; 
            x = x << 1; //left shifting doubles 
        }
        return prod;
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
        EquationList last = new EquationList(equation, result, eqList);
        eqList = last; 
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
        // EquationList allHistory = eqList;
        // while (allHistory != null) {
        //     System.out.println(allHistory.equation + " = " + allHistory.result);
        //     allHistory = allHistory.next;
        // }


        //attempt to take out creating new list
        while (eqList != null) {
            System.out.println(eqList.equation + " = " + eqList.result);
            eqList = eqList.next;
        }

        //attempting to write special case of printHistory
        // EquationList allHistory = eqList; 
        // allHistory.printHistory(length(allHistory));
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
        //for loop increment to n
        // EquationList nHistory = eqList;
        // for (int i = 1; i <= n; i += 1) {
        //     System.out.println(nHistory.equation + " = " + nHistory.result);
        //     nHistory = nHistory.next;
        // }

 
        //attempt to take out creating new list

        for (int i = 1; i <= n; i += 1) {
            System.out.println(eqList.equation + " = " + eqList.result);
            eqList = eqList.next;
        }        
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (eqList != null) { //THIS IS NEW
            eqList = eqList.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqList = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        // EquationList history = this.eqList;
        // int count = 0;
        // while (history != null) {
        //     count += history.result;
        //     history = history.next;
        // }
        // return count;


        //attempt to take out creating new list
        int count = 0;
        while (eqList != null) {
            count += eqList.result;
            eqList = eqList.next;
        }
        return count;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
    //     EquationList history = this.eqList;
    //     int prod = 1;
    //     while (history != null) {
    //         prod *= history.result;
    //         history = history.next;
    //     }
    //     return prod;
    // }

        //attempt to take out creating new list
        int prod = 1;
        while (eqList != null) {
            prod *= eqList.result;
            eqList = eqList.next;
        }
        return prod;
    }
}

