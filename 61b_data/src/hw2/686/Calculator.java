import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList updateEquation = null;
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
        // YOUR CODE HERE
        if (y == 0)
        {
            return x;
        }
        
        if (x == 0)
        {
            return y;
        }
        
        int xor = (x ^ y);
        int and = ((x & y) << 1);
        
        return add(xor, and);
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
        // YOUR CODE HERE
        int mul = 0;
        if (x == 0 || y == 0)
        {
            return 0;
        }
        
        if (x == 1)
        {
            return y;
        }
        
        if (y == 1)
        {
            return x;
        }
        
        for (int i = 0; i < y; i++)
        {
            mul = add(mul, x);
        }
        return mul;
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
       // YOUR CODE HERE
       updateEquation = new EquationList(equation, result, updateEquation);     
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
        printHistory(Integer.MAX_VALUE);
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
        while (n != 0 && updateEquation != null)
        {
            System.out.println(updateEquation.equation + " = " + Integer.toString(updateEquation.result));
            updateEquation = updateEquation.next;
            n--;
        }
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (updateEquation != null)
        {
           updateEquation = updateEquation.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory()
    {
        // YOUR CODE HERE
        while (updateEquation != null)
        {
            updateEquation = updateEquation.next;
        }
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
        if (updateEquation == null)
        {
            return 0;
        }
        
        while (updateEquation != null)
        {
            sum = add(sum, updateEquation.result); 
            updateEquation = updateEquation.next;
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
        if (updateEquation == null)
        {
            return 0;
        }
        
        while (updateEquation != null)
        {
            product = multiply(product, updateEquation.result);
            updateEquation = updateEquation.next;
        }
        return product;
    }
}
