import list.EquationList;

public class Calculator {
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
        while (y != 0) {
            int remainder = x & y; // AND, when both bits are 1's, the "remainder" sends a 1 to the bit above /
                                        
            x = x ^ y; // XOR operator, x becomes an exclusive mix of x and y. e.g 1100 ^ 1010 = 0110 

            y = remainder << 1; // this part sends the overlap of x & y to the bit above 
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
        int product = 0; // iterated over many times

        while (y != 0) { //if y is 0 just return 0
            if ((y & 1) != 0) { // gives the last bit of y, if it is 1...
                product = add(x, product); //...we add the weighted amount of x to the product
            }

            x = x << 1; // every iteration x is weighted by essentially times 2, this is so that multiplying by 0b100 gives 8 times the amount as 0b001 

            y = y >>> 1; // we move on to the next position of y, eventually will be 0. >> and >>> interchangable(?)

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

    EquationList current = null;

    public void saveEquation(String equation, int result) {
        current = new EquationList(equation, result, current);
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = current;
        while (temp != null) {
            System.out.print(temp.equation + " = ");
            System.out.println(temp.result);
            temp = temp.next;
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
        EquationList temp = current;
        while ((temp != null) && (n > 0)) {
            System.out.print(temp.equation + " = ");
            System.out.println(temp.result);
            temp = temp.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (current != null) {
        current = current.next;}
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        current = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temp = current;
        while (temp != null) {
            sum = sum + temp.result;
            temp = temp.next;
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
        int product = 1;
        EquationList temp = current;
        while (temp != null) {
            product = product * temp.result;
            temp = temp.next;
        }
        return product;
    }
}