import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList currentList = null;

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
        /* By using bitwise operators, Java automatically converts integers 
        /* into binary numbers. => Can treat X and Y as binary numbers */
        while (y != 0) { //repeat till there is nothing left to carry over to Y
            
            int carry = (x & y); //If both bits equals one, then one

            x = x ^ y; //Sum all bits, except for ones that's already been carried over

            y = carry << 1; //Shifts carry by one bit for next iteration to be summed with X
        }
    return x;
    //Through the iteration, sum of two number is stored in X
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
        int result = 0; // Stores sum so far.
        while (y != 0) { // Repeat untill all bits in b is handled.
            if ((y & 1) != 0) { /* Use & operator to determine i if current bit is zero.
                                 * If so, skip, otherwise, add cummulated result to x */
                result = add(result, x);
            }
            x = x << 1; //After iteration, shift left by 1.
            y = y >>> 1;  //After iteration, shift right by 1, also leaving top bit unsigned.
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
        // YOUR CODE HERE
        if (currentList == null) {
            currentList = new EquationList(equation, result, null);
        } else {
            EquationList pointer = currentList;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = new EquationList(equation, result, null);
        }
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/

    private EquationList reverse(EquationList lst) {
        EquationList reversed = null;
        while (lst != null) {
            reversed = new EquationList(lst.equation, lst.result, reversed);
            lst = lst.next;
        }
        return reversed;
    }

    public void printAllHistory() {
        // YOUR CODE HERE
        EquationList reversed = reverse(currentList);
        while (reversed != null) {
            System.out.println(reversed.equation + " = " + String.valueOf(reversed.result));
            reversed = reversed.next;
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
        int k = 0;
        EquationList reversed = reverse(currentList);
        while (k < n) {
            System.out.println(reversed.equation + " = " + String.valueOf(reversed.result));
            reversed = reversed.next;
            k += 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList pointer = currentList;
        while (pointer.next.next != null) {
            pointer = pointer.next;
        }
        pointer.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        currentList = null;
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
        EquationList pointer = currentList;
        while (pointer != null) {
            sum = sum + pointer.result;
            pointer = pointer.next;
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
        EquationList pointer = currentList;
        while (pointer != null) {
            product = product * pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}























