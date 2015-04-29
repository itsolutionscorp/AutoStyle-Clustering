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
        // YOUR CODE HERE
        while (x != 0) {
            int hold = x ^ y;  
            int carry = x & y;  //Determine 1's to be carried over.
            carry = carry << 1; //move the carry over 1 to the left
            x = carry;
            y = hold;
            /* Repeat, with updated columns, 
               until nothing is left to carry over. */
        }
        return y;
      
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
        boolean neg1 = false;
        boolean neg2 = false;
        //Determine if values are negative; if so, make them positive
        if (x < 0) {
            neg1 = true;
            x = add((~x), 1); 
        }
        if (y < 0) { 
            neg2 = true;
            y = add((~y), 1);
        }
        boolean neg = neg1 ^ neg2; //XOR
        if (x < y) { //make x >= y
            int a = x;
            x = y;
            y = a;
        }

        int hold = 0;
        while (y != 0) {
            if ((y & 1) != 0) //if odd
                hold = add(hold, x);
            x = x << 1; //multiply by two
            y = y >> 1;
        }
        if (neg == true)
            return add((~hold), 1); //Convert to negative if need be
        return hold;
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
    public EquationList store; //holds the history
    public int first = 0;
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (first == 0) {
        first += 1;
        store = new EquationList(equation, result, null);
        }
        else {
            store = new EquationList(equation, result, store);
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
        // YOUR CODE HERE
        int count = 0;
        EquationList ptr = store;
        while (ptr != null) {
            count += 1;
            ptr = ptr.next; 
        }
        printHistory(count);
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

        EquationList ptr = store;
        
        while (n > 0 && ptr != null) {
            System.out.println(ptr.equation + " = " + ptr.result);
            ptr = ptr.next;
            n = n - 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (store == null) //If no equations entered, do nothing
            return;
        store = store.next;
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        store = null;
        first = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList ptr = store;
        int sum = 0;
        while (ptr != null) {
            sum = add(sum, ptr.result);
            ptr = ptr.next;
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
        EquationList ptr = store;
        int product = 1;
        while (ptr != null) {
            product = multiply(product, ptr.result);
            ptr = ptr.next;
        }
        return product;
    }
}
