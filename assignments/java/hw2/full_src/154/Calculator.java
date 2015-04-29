import list.EquationList;

public class Calculator {
    public EquationList history = new EquationList("", 0, null);

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while (y != 0)
        {
            int carry = x & y;
            x = x ^ y;
            y = carry << 1;
        }
        return x;
    }

    /** Negating a number by calculating the two's complement */
    public int negate(int x)
    {
        return add(~x, 1);
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
        int sum = 0, z = 1;
        if (y < 0)
        {
            x = negate(x);
            y = negate(y);
        }
        while (y >= z && x != 0)
        {
            if ((y & z) != 0)
                sum = add(sum, x);
            x <<= 1;
            z <<= 1;
        }
        return sum;
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
        EquationList saved = new EquationList(equation, result, history.next);
        history.next = saved;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        printHistory(historyCount());
    }

    /** Returns the number of past commands entered into the calculator */
    public int historyCount()
    {
        int count = 0;
        EquationList copy = history.next;
        while (copy != null)
        {
            count++;
            copy = copy.next;
        }
        return count;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList copy = history.next;
        for (int i = 0; i < n && i < historyCount(); i++)
        {
            System.out.println(copy.equation + " = " + copy.result);
            copy = copy.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (historyCount() == 0)
            history.next = null;
        else
            history.next = history.next.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history.next = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList copy = history.next;
        int sum = 0;
        if (copy == null)
            return sum;
        else
        {
            while (copy != null)
            {
                sum += copy.result;
                copy = copy.next;
            }
            return sum;
        }
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList copy = history.next;
        int product = 1;
        if (copy == null)
            return product;
        else
        {
            while (copy != null)
            {
                product *= copy.result;
                copy = copy.next;
            }
            return product;
        }
    }
}