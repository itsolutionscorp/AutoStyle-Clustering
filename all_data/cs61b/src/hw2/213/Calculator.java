import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList history;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y) 
    {
        // Result, carry, and mask variables
        int result = 0;
        int carryValue = 0;
        int mask = 1;

        // Loop through each bit position from right to left(purpose of mask)
        for (int i = 0;i < 32; ++i)
        {
            // Update new bit for result variable (carry xor x xor y)
            result = ((carryValue ^ x ^ y) & mask) | result;

            // Set the new carry value (xy + carry(x + y))
            carryValue = (((x & y) | (carryValue & (x | y))) & mask) << 1;

            // Shift the mask to the next(left) bit 
            mask = mask << 1; 

        }

        return result;
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/
    public int multiply(int x, int y) 
    {
        int result = 0;
        // Loop through all the bits
        for (int i = 0; i < 32 && y != 0; ++i)
        {
            // Get lsb of y
            int lsb = y & 1;
            y = y >> 1;

            // If lsb is 1, then add x to result
            if (lsb == 1)
            {
                result = add(result, x);
            }

            // Shift x to left by 1 bit
            x = x << 1;
        }
        // Return x
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
    public void saveEquation(String equation, int result) 
    {
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList pointer = history; 
        while (pointer != null)
        {
            System.out.println (pointer.equation + " = " + pointer.result);
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
    public void printHistory(int n) 
    {
        EquationList pointer = history; 
        while (pointer != null && n-- > 0)
        {
            System.out.println(pointer.equation + " = " + pointer.result);
            pointer = pointer.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() 
    {
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() 
    {
        int result = 0;
        EquationList pointer = history;
        while (pointer != null)
        {
            result += pointer.result;
            pointer = pointer.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() 
    {
        int result = 1;
        EquationList pointer = history;
        while (pointer != null)
        {
            result *= pointer.result;
            pointer = pointer.next;
        }
        return result;
    }
}