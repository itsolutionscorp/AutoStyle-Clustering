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
            int b = x^y;
            y = x&y;
            y = y<<1;
            x = b;
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
        int result = 0;
        while (y != 0) {
            if ((y & 1)==1) { //if y = odd, then you just add x to result
                result = add(x, result);
                }
            y = y >>> 1;//y/2 //if y = odd, then y = y -1
            x = x << 1; //x/2
    }
    return result;
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
    EquationList saved = null;    
    public void saveEquation(String equation, int result) {
        EquationList newsaved = new EquationList(equation, result, saved); 
        saved = newsaved;
    }
     /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList saved2 = saved;
    while (saved2 != null) {
        System.out.println(saved2.equation + " = " + Integer.toString(saved2.result));
        saved2 = saved2.next;
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
        EquationList saved2 = saved;
        while ((n > 0) && (saved2 != null)) {
            System.out.println(saved2.equation + " = " + Integer.toString(saved2.result));
            saved2 = saved2.next;  
            n -= 1;
            }    
}

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        saved = saved.next;
        }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        saved = null;   
        }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList saved2 = saved;
        while (saved2 != null) {
            sum = add(saved2.result, sum);
            saved2 = saved2.next;
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
        EquationList saved2 = saved;
        int product = 1;
            while (saved2 != null) {
                product = multiply(saved2.result, product);
                saved2 = saved2.next;
            }
            return product;
        }    

   
}
