import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqlist = null;

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
    // Get the idea from Computerphile, binary episode.
        while (y != 0 ) {
            int z = ( x & y );
            x = x ^ y ;
            y = z << 1; 
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
        int tempX  = x;
        int tempY  = y;
        int answer = 0; 
        if (x == 0 || y == 0) return x;
        while (tempY != 0) {
            if ((tempY & 01) == 1) {
                answer = add(answer, tempX);        
            }        
            tempX <<= 1 ;
            tempY >>= 1 ;
        }
        return answer ;
    }

    /**
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     */
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        eqlist = new EquationList(equation, result, eqlist);
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
        EquationList temp = eqlist;
        while (temp != null) {       
            System.out.println( temp.equation  + " = " + temp.result) ;
            temp = temp.next ;
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
        // YOUR CODE HERE note: n is a positive integer 
        EquationList temp2 = eqlist;
        int i = 0;
        while (n != i) {       
            System.out.println( temp2.equation  + " = " + temp2.result) ;
            temp2 = temp2.next ;
            i ++;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList temp3 = eqlist;
        eqlist = eqlist.next;
//        eqlist = new EquationList(equation, result, null);
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqlist = null ;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum =0;
        EquationList temp4 = eqlist;
        while (temp4 != null) {       
            sum += temp4.result;
            temp4 = temp4.next ;
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
        // YOUR CODE HERE
        int prod = 1 ;
        EquationList temp5 = eqlist;
        while (temp5 != null) {       
            prod *= temp5.result;
            temp5 = temp5.next ;
        }
        return prod;
    }
}
