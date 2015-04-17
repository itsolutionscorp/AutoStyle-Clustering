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
    public int add(int x, int y) 
    {
    int i = 0;
    int result = 0;
    int c = 0;
    while (i < 32) 
        {
        int a = getBit(x, i);
        int b = getBit(y, i);
        int carryHolder = helperMajority(a, b, c);
        int placeHolder = a ^ b;
        int cResult = placeHolder ^ c;
        result = (cResult << i) ^ result;
        if (placeHolder == 1 && c == 1) 
        {
            c = 1;
        }
        else {c = carryHolder;}
        i = i + 1;
        }
    return result;
    }
    
    private int getBit(int x, int position) 
        {
            int val = x >> position;
            int result = val & 1;
            return result;
        }
    
    private int helperMajority(int a, int b, int c) 
    {
        int x = a & b;
        if (x == 0) 
        {
            c = 0;
        }
        if (x == 1) 
        {
            c = 1;
        }
        return c;
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
        int loopCount = 1;
        int product = 0;

        while (loopCount < y) 
        {
            product =  x << 1;
            loopCount = loopCount*2;
        }
    
        while (loopCount != y) 
        {
            product = add(product, -x);
            loopCount = add(loopCount, -1);
        }

        if (y < 0) 
        {
            return add(product, x);
        }

        else {
            return product;
        }
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
    EquationList history = null;

    public void saveEquation(String equation, int result) {
        this.history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList tracker = history;
        while (tracker != null) {
            System.out.println(tracker.equation + " = " + tracker.result);
            tracker = tracker.next;
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
        EquationList tracker2 = history;
        while (n > 0 && tracker2 != null) {
            System.out.println(tracker2.equation + " = " + tracker2.result);
            tracker2 = tracker2.next;
            n--;
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
    public void clearHistory() {
        history = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList tracker = history;
        while (tracker != null) {
            sum = tracker.result + sum;
            tracker = tracker.next;
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
        int product = 1;
        EquationList tracker = history;
        while (tracker != null) {
            product = history.result * product;
            tracker =  tracker.next;
        }
        return product;
    }
}