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
        if (x == y) {   
            return (x << 1);    // if the ints are the same, just double
        }   
        int c;
        while (y != 0) {
            c = x & y;  
            x = x ^ y;
            y = c << 1;
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
        int result;
        if ((x == 0) || (y == 0)) {
            return 0;
        } else if (x == 1) {
            return y;
        } else if (y == 1) {
            return x;
        } else if (((x >>> 31) == 1) && ((y >>> 31) == 1)) {  // if both negative
            x = add(x, -1);
            x = ~x;
            y = add(y, -1);
            y = ~y;
            return multiplyPositives(x, y);
        } else if ((x >>> 31) == 1) {       // if -x
            x = add(x, -1);
            x = ~x;
            result = multiplyPositives(x, y);
            result = ~result;
            result = add(result, 1);
            return result; 
        } else if ((y >>> 31) == 1) {       // if -y
            y = add(y, -1);
            y = ~y;
            result = multiplyPositives(x, y);
            result = ~result;
            result = add(result, 1);
            return result; 
        } else {
            return multiplyPositives(x, y);
        }
    }

    private int multiplyPositives(int x, int y) {
        int a = x;             // shift ### to ###0
        int b = y;             
        int result = 0;
        while (b != 0) {
            if ((b & 1) == 1) {     // if right-most bit is 1        
                result = add(result, a);
            }
            a = a << 1;             // shift ### to ###0 
            b = b >> 1;              
    
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
    public void saveEquation(String equation, int result) {
        if (eqlist == null) {
            eqlist = new EquationList(equation, result, null);
        } else {
            eqlist = new EquationList(equation, result, eqlist);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory((int) 100e100);    // Assuming the user doesn't store
    }                                   // extraordinary amount of history

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (eqlist == null) {
            
        }
        EquationList temp = eqlist;
        while ((n > 0) && (temp != null)) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n--;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eqlist = eqlist.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        while (eqlist != null) {
            eqlist.equation = "";
            eqlist.result = 0;
            eqlist = eqlist.next;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList temp = eqlist;
        while (temp != null) {
            sum += temp.result;
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
        int product = 1;
        EquationList temp = eqlist;
        while (temp != null) {
            product *= temp.result;
            temp = temp.next; 
        }
        return product;
    }
}