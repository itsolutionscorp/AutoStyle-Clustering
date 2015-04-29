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
    EquationList total;
    EquationList current;
    
    
    public int add(int x, int y) {
        int placeholder = x;
        if (x == 0) {
            return y;
        }
        if (y == 0) {
            return x;
        }
        else{
            x = x ^ y;
            y = (placeholder & y) << 1;
            return add(x,y);
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
        int sum = 0;
        while (y != 0) {
            if ((y & 1) != 0) {
                sum = add(sum, x);
            }
            x = x << 1;
            y = y >>> 1;
        }
        return sum;

        // int counter = 0;
        // int sum = 0;
        // if (x == 0 || y == 0) {
        //     return 0;
        // }
        // while (y != 0) {
        //     if ((y & 1) == 1) {
        //         sum = add(sum, x << counter);
        //     }

        //     counter = add(counter,1);
            
        //     y = y >> 1;
        // }
        // return sum;
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
        if (total == null){
            total = new EquationList(equation, result, null);
            current = total;
        }
        else {
            current.next = new EquationList(equation, result, null); 
            current = current.next;
        }


        // now = start;
        // now = new EquationList(equation,result, null);
        // equationlst = now;
        // EquationList beta = equationlst;
        // beta = beta.next;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList m = null;
        EquationList copy = total;
        while (copy != null) {
            m = new EquationList(copy.equation, copy.result, m);
            copy = copy.next;
        }
        while (m != null) {
            System.out.println(m.equation + " = " + m.result);
            m = m.next;
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
        EquationList m = null;
        EquationList copy = total;
        while (copy != null) {
            m = new EquationList(copy.equation, copy.result, m);
            copy = copy.next;
        }
        int counter = 0;
        while (counter < n){
            System.out.println(m.equation + " = " + m.result);
            m = m.next;
            counter += 1;

        }
    }
        

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList pointer = null;
        pointer = total;
        if (pointer.next == null) {
            total = null;
        }
        else {
            while (pointer.next.next != null) {
                pointer = pointer.next;
            }
            pointer.next = null;

        }
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        total = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList alpha = total;
        if (alpha == null) {
            return 0;
        }
        else {
            while (alpha != null) {
                sum += alpha.result;
                alpha = alpha.next;
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
        int product = 1;
        EquationList beta = total;
        if (beta == null) {
            return 1;
        }
        else {
            while (beta != null) {
                product *= beta.result;
                beta = beta.next;
            }
        }
        return product;
    }
}