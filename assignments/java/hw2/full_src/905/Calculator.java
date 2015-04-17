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

    EquationList history = null;

    public int getBit(int x, int n) {
        // Returns the nth bit of x, where the @ the rightmost bit n = 0
        return (x >>> n) & 1;
    }

    public int setBit(int x, int n) {
        return x | (1 << n);
    }

    public int add(int x, int y) {
        // YOUR CODE HERE
        int carry = 0, result = 0;
        int xcheck, ycheck;
        for (int i = 0; i <= 31; i++) {
            xcheck = getBit(x, i);
            ycheck = getBit(y, i);
            if ((xcheck^ycheck^carry) == 1) {
                result = setBit(result, i);
            }
            if (((xcheck & ycheck) == 1) || ((ycheck & carry) == 1) 
                || ((xcheck & carry) == 1)) {
                carry = 1;
            }
            else {
                carry = 0;
            }            
        }
        return result;  
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
        int total = 0;
        int ycheck;
        for (int i = 0; i < 32; i++) {
            ycheck = getBit(y, i);
            if (ycheck == 1) {
                total = add(x << i, total);
            }
        }
        return total;
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
        // YOUR CODE HERE
        EquationList localH = history;
        while (localH != null) {
            System.out.println(localH.equation + " = " + localH.result);
            localH = localH.next;           
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
        EquationList localH = history;
        for (int i = 0; i < n && localH != null; i++) {
            System.out.println(localH.equation + " = " + localH.result);
            localH = localH.next;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
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
        EquationList localH = history;
        while (localH != null) {
            sum = add(localH.result, sum);
            localH = localH.next;
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
        EquationList localH = history;
        while (localH != null) {
            product = multiply(localH.result, product);
            localH = localH.next;
        }
        return product;
    }
}