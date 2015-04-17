import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS

    public EquationList hist;

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
        /* add sum where there is only 0 and 1
        * then find carriers (ie. 1 and 1 carries 1)
        * shift carrier then add to previously added sum
        * now find if this leftover has any carriers
        * repeat until 0 carriers
         */

        //The TAs in office hours helped me a lot to understand the implementation of the problem and essentially explained each step to me
        //all I have to do is write the code out from english to java
        int addnext = (x ^ y);
        int carrier = (x & y);

        while (carrier != 0) {
            int nextcarrier = carrier << 1;
            carrier = (addnext & nextcarrier);
            addnext = (addnext ^ nextcarrier);
        }

        return addnext;
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
        /* use the add function to take x and add x to itself y times
        *keep doing until hit y times
        * check if negative, then make x negative
         */

        int xtemp = x; //xtemp = 2
        for (int i = 1; i<Math.abs(y); i++) {
            x = add(xtemp, x);
        }
        if (y == 0) {
            return 0;
        }
        if ((y >>> 31) == 1) { // checks if negative, then make x negative, was unsure if can use "y<0" so checked negative using bitwise operations
            x = ~x;
            x = add(x,1);
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


    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE

        hist = new EquationList(equation, result, hist);

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

        EquationList histtemp = hist;

        while (histtemp != null) {
            System.out.println(histtemp.equation + " = " + histtemp.result);
            histtemp = histtemp.next;
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

        EquationList histtemp = hist;
        int i = 0;

        while (i < n) {
            System.out.println(histtemp.equation + " = " + histtemp.result);
            histtemp = histtemp.next;
            i++;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        hist = hist.next;
    }
    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        hist = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE

        int sum = 0;

        EquationList histtemp = hist;

        if (hist == null) {
            return sum;
        }
        else {
            while (histtemp != null) {
                sum = add(sum, histtemp.result);
                histtemp = histtemp.next;
            }
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
        int prod = 1;

        EquationList histtemp = hist;

        if (hist == null) {
            return prod;
        }
        else {
            while (histtemp != null) {
                prod = multiply(prod, histtemp.result);
                histtemp = histtemp.next;
            }
        }

        return prod;
    }
}