import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS  
    private EquationList hi;
    private EquationList pointer;

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
        int temp, carryShift, carry;
        temp = x ^ y;
        carry = x & y;
        carryShift = carry << 1;
            while (carryShift != 0) {
                carry = carryShift & temp;
                temp = temp ^ carryShift;
                carryShift = carry << 1;
            }
        return temp;
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
        int x1, z, i;
        z = 0;
        if (y == 1) {
            return x;
        }
        if (y == -1) {
            return -x;
        }
        
        if (y < -1) {           
            x1 = x;
            z = add(x1, x);
            for (i = 0; i < Math.abs(y) - 2; i = i + 1) {
                z = add(z, x1);
            }
            z = -z; 
        } else if (y >= 1) {
            x1 = x;
            z = add(x1, x);
            for (i = 0; i < y - 2; i = i + 1) {
                z = add(z, x1);
            }   
        }
        return z;
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
        if (hi == null) {
        hi = new EquationList(equation, result, null);
        }
        //pointer = hi;
        //System.out.println("pointer = "  + pointer);
        pointer = new EquationList(equation, result, hi);
        hi = pointer;
        //pointer = new EquationList(equation, result, null);
        //pointer = pointer.next;
        //System.out.println("new pointer = " + pointer);
        //System.out.println(hi);
        
/*
        pointer = hi;
        if (hi == null) {
            hi = new EquationList(equation, result, null);
        } else {       
        pointer.next = new EquationList(equation, result, null);
        }
        */
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
        if (hi != null) {
            pointer = hi;
            while (pointer.next != null) {
                System.out.println(pointer.equation + " = " + pointer.result);
                pointer = pointer.next;
            }
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
        if (hi != null) {
            int i = 0;
            pointer = hi; 
            while (i < n) {
                System.out.println(pointer.equation + " = " + pointer.result);
                pointer = pointer.next;
                i = i + 1;   
            }
        }        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        hi = hi.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        hi = null;

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
        if (hi != null) {
            pointer = hi;
            while (pointer.next != null) {
                sum = sum + pointer.result;
                pointer = pointer.next;
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
        int product = 1;
        
        if (hi != null) {
            pointer = hi;
            while (pointer.next != null) {
                product = product * pointer.result;
                pointer = pointer.next;
            }
        }
        return product;
    }
}