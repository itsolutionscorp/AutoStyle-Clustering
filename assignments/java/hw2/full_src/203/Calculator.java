import list.EquationList;

public class Calculator {
    public EquationList equations;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // Flip (^) only outputs a 1 when a column has one 1.
        // Therefore, used for adding (however, doesn't do the carry overs).
        int addSimple = x ^ y;

        // Mask (&) determines where 1+1 occurs.
        // Therefore, we shall find when we will need to carry over a number.
        // Shifting carryOver so that it can be added to addSimple.
        int carryOver = (x & y);
        int shiftToAdd = carryOver << 1;

        while (shiftToAdd != 0) {
            // Saving the old addSimple so that it can be used to adjust
            // shiftToAdd with a new addSimple.
            int oldSimple = addSimple;

            // Addition in that it only adds together 0+1's and the reverse.
            addSimple = shiftToAdd ^ oldSimple;

            // This determines what carries over after adding what carries over from above.
            shiftToAdd = (oldSimple & shiftToAdd) << 1;
        } 
        return addSimple;
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
        // Starting total at zero, so that we can add x, y amount of times.
        // Shift starts at 0, because the one's place represents 2^(0).
        int total = 0;
        int shift = 0;

        while (shift < 32) {

            // The first part determines whether or not there is a 1 in
            // a certain column. If so, add x * 2^(shift).
            if (((1 << shift) & y) != 0) {
                total = add(total, x << shift);
            }
            shift = add(shift, 1);
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
        String fullEquation = equation + " = " + Integer.toString(result);
        equations = new EquationList(fullEquation, result, equations);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointer = equations;
        while (pointer != null) {
            System.out.println(pointer.equation);
            pointer = pointer.next;
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
        EquationList pointer = equations;
        while (pointer != null) {
            if (pointer.result == n) {
                System.out.println(pointer.equation);
            }
            pointer = pointer.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() { 
        if (equations != null) {
            equations = equations.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = equations;
        int sum = 0;
        while (pointer != null) {
            sum += pointer.result;
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
        EquationList pointer = equations;
        int product = 1;
        while (pointer != null) {
            product *= pointer.result;
            pointer = pointer.next;
        }
        return product;
    }
}