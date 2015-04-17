import list.EquationList;

public class Calculator {
    EquationList eqList;
    static int sizeOfInt = Integer.SIZE;
    
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
        // 0101
        // 0011
        // 1000
        // YOUR CODE HERE
        int carryIn = 0;
        int n = 0;
        int z = 0;
        while (n < sizeOfInt) {
            int last_bitX = x & 1;
            int last_bitY = y & 1;

            // Case when last bits of x and y are 1's along with CarryIn = 1
            if ((last_bitX & last_bitY & carryIn) == 1) {
                carryIn = 1;
                z = z | (0x1 << n);
            }
            // If either one is 1
            else if ((last_bitY ^ last_bitX ^ carryIn) == 1) {
                carryIn = 0;
                z = z | (0x1 << n);
            }
            // Update CarryIn value if both X  and Y bits are 1's
            else if ((last_bitY & last_bitX) == 1 && carryIn == 0) {
                carryIn = 1;
            }
            // Shift X and Y
            x = x >> 1;
            y = y >> 1;
            n += 1;
            }
        return z;
        }
    

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise opesumrators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int product = 0;
        int n = 0;
        while (n < sizeOfInt) {
            int last_bitY = y & 0x1;
            if (last_bitY == 1) {
                product = add(product, x);
            }
            x = x << 1;
            y = y >> 1;
            n += 1;
        }
        return product;
        //return -1;
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
        if (eqList == null) {
        eqList = new EquationList(equation, result, null);
    }
    else {
        eqList = new EquationList(equation, result, eqList);
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
        EquationList ptr = eqList;
        int i = 0;
        while(ptr != null) {
            i += 1;
            ptr = ptr.next;
        }
        printHistory(i);
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
        EquationList ptr = eqList;
        while(n > 0) {
            System.out.println(ptr.equation + " = " + ptr.result);
            n-= 1;
            ptr = ptr.next;
        }
        } 

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (eqList.next != null) {
        eqList = eqList.next;
        }
        else {
            clearHistory();
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        eqList = null;
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
        if (eqList == null) {
            return 0;
        }
        else {
            EquationList pointer = eqList;
            while (pointer != null) {
                sum += pointer.result;
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
        if (eqList == null) {
            return 1;
        }
        else {
            EquationList pointer = eqList;
            while (pointer != null) {
                product *= pointer.result;
                pointer = pointer.next;
            }
        }
        return product;
    }
}