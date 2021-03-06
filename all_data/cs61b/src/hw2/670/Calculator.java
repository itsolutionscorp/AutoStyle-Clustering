import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList el = null;
    public int current = 0;
    public int procurrent = 1;
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
        if (y == 0) {

            return x; 
        }
        else {
            int z = x&y;
            int c = x^y;
            z = z<<1;
            return add(c,z);
        }
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
        if ((y < 0) && (x < 0)) {
            x = ~x;
            x = add(x,1);
            y = ~y;
            y = add(y,1);
            return multiply(x,y);

        }

        else if (y < 0) {
            return multiply(y,x);

        }
                 else {

        int total = 0;
        while (y != 0) {
            total = add(x, total);
            y = add(y,-1);
        }
        return total;
    }

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

        el = new EquationList(equation,result,el);
        current = current + el.result;
        procurrent = procurrent * el.result;
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
        if (el == null) {
            return;
        }
        EquationList ell = el;
        cumulativeSum();
        cumulativeProduct();
        while (ell.next != null) {
        System.out.println(ell.equation + " = " + ell.result);
        ell = ell.next;
    }
        System.out.println(ell.equation + " = " + ell.result);
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
        if (el == null) {
            return;
        }
        EquationList ell = el;
        while (n != 0) {
            n = n - 1;
            System.out.println(ell.equation + " = " + ell.result);
            ell = ell.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        current = current - el.result;
        procurrent = procurrent/ el.result;
        el = el.next;
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        el = null;
        current = 0;
        procurrent = 1;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        //YOUR CODE HERE

        return current;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        return procurrent;
    }
        
}