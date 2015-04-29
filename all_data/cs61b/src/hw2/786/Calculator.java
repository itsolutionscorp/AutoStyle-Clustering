import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList equationl;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y){
        int a = x ^ y;
        int b = (x & y) << 1;
        if (b == 0)
            return a; 
        else {
            return addhelper(a, b);
        }
    }
    public int addhelper(int x, int y){
        int c = x & y;
        int d = x | y;
        return add(c, d);
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
        if (x == 0 || y == 0){
            return 0;
        }
        if (x < 0 && y < 0){
            return multiply((add(1, ~x)), (add(1, ~y)));
        }
        if (x < 0){
            return add(1, ~(multiply((add(1, ~x)), y)));
        }
        if (y < 0){
            return add(1, ~(multiply(x, (add(1, ~y)))));
        }
        if (x == 1){
            return y;
        }
        /* if x is odd */
        if ((x & 1) == 1){
            return add(y, multiply((add((add(1, ~1)), x)>>1), y<<1));
        }
        return multiply(x>>1, y<<1);
    }

    /*
     * TASK 5A: CALCULATOR HISTORY - IMPLEMENTING THE HISTORY DATA STRUCTURE
     * saveEquation() updates calculator history by storing the equation and 
     * the corresponding result.
     * Note: You only need to save equations, not other commands.  See spec for 
     * details.
     * @param equation is a String representation of the equation, ex. "1 + 2"
     * @param result is an integer corresponding to the result of the equation
     **/
    public void saveEquation(String equation, int result) {
        equationl = new EquationList(equation, result, equationl);
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
        EquationList holder = equationl;
        int counter = 0;
        while (holder != null){
            counter += 1;
            holder = holder.next;
        }
        printHistory(counter);
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
        EquationList holder = equationl;
        while (n > 0){
            System.out.println(holder.equation + " = " + holder.result);
            holder = holder.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        equationl = equationl.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equationl = null;    
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList holder = equationl;
        int sum = 0;
        while (holder != null){
            sum += holder.result;
            holder = holder.next;
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
        EquationList holder = equationl;
        int product = 1;
        while (holder != null){
            product *= holder.result;
            holder = holder.next;
        }
        return product;
    }
}