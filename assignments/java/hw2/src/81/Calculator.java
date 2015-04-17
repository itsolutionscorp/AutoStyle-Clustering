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

    public EquationList equations = new EquationList(null, 123, null);

    public int add(int x, int y) {
        int carryPositions = (x & y) << 1;
        int zeroInCarry = x ^ y;
        int combined = carryPositions | zeroInCarry;

        if (combined != (carryPositions ^ zeroInCarry))
        {
            combined = add(carryPositions, zeroInCarry);
        }
        
        return combined;
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
        if (x == 0 || y == 0)
        {
            return 0;
        }

        int fixedVal = x;
        int index = 1;
        /*take absolute value of y in case it is negative*/
        int yAbs = (y ^ (y >> 31)) - (y >> 31);
        
        while (index < yAbs) 
        {
            x = add(x, fixedVal);    
            index += 1;
        }

        int xAbs = (x ^ (x >> 31)) - (x >> 31);

        if ((x < 0 && y < 0) || (x > 0 && y > 0))
        {
            return xAbs;             
        }
        return -xAbs;
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
        
        EquationList nextEquation = new EquationList(equation, result, equations.next);
        equations.next = nextEquation;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int length = 0;
        EquationList pointer = equations;
        
        while (pointer.next != null)
        {
            pointer = pointer.next;
            length += 1;
        }
        
        printHistory(length);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        int index = 0;
        EquationList pointer = equations;

        while (index < n)
        {
            pointer = pointer.next;
            System.out.println(pointer.equation + " = " + pointer.result);
            index += 1;
        }   
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (equations.next != null)
        {
            equations = new EquationList(equations.equation, equations.result, 
                                        equations.next.next);
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equations = new EquationList(null, 123, null);
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
      
        while (pointer.next != null)
        {
            pointer = pointer.next;
            sum += pointer.result;    
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
        EquationList pointer = equations;
        int product = 1;
      
        while (pointer.next != null)
        {
            pointer = pointer.next;
            product *= pointer.result;    
        }

        return product;
    }
}