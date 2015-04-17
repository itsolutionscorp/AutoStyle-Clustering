import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS.
    public EquationList history;

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
        while (y != 0)
        {
            int carrier = x & y; //what needs to be carried
            x = x ^ y; //A + B
            y = carrier << 1; //carry forward
        }

        return x;
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
        int answer = 0;

        while (x != 0)               
        {
            if ((y & 1) != 0) 
            {          
                answer = answer + x;
            } 

            x = x << 1;              
            y = y >> 1;          
        }

        return answer;
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

        if (history == null)
        {
            history = new EquationList(equation, result, null);
        }
        else
        {
            history = new EquationList(equation, result, history);
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
        EquationList copy = history;

        if (copy == null)
        {
            return;
        }

        else
        {
            while (copy.next != null)
            {
                System.out.println(copy.equation + " = " + copy.result);
                copy = copy.next;
            }
            System.out.println(copy.equation + " = " + copy.result);
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
        EquationList copy = history;

        if (copy == null || n == 0) 
        {
            return;
        }

        else
        {
            while (n != 1 && copy.next != null)
            {
                System.out.println(copy.equation + " = " + copy.result);
                copy = copy.next;
                n--;
            }
            System.out.println(copy.equation + " = " + copy.result);
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        history = history.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        history = null;
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
        
        if (history == null)
        {
            return sum;
        }

        EquationList copy = history;
        while (copy.next != null)
        {
            sum += copy.result;
            copy = copy.next;
        }

        return sum + copy.result;
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
        
        if (history == null)
        {
            return 0;
        }

        EquationList copy = history;
        while (copy.next != null)
        {
            product *= copy.result;
            copy = copy.next;
        }

        return product * copy.result;
    }
}