import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList theList;

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
        int a = x;
        int b = y;
        int temp;
        int carry = a & b;
        int result = a ^ b;
        while (carry != 0){
            carry <<= 1;
            temp = result^carry;
            carry &= result;
            result = temp;
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
    public int multiply(int x, int y) {
        // YOUR CODE HERE
        int a = x, b = y, result=0;
        int is_neg;
        if (a < 0 && b < 0)
        {
            a = ~a;
            b = ~b;
            a = add(a, 1);
            b = add(b, 1);
        }
        else if(a > 0 && b < 0)
        {
            int temp = a;
            a = b;
            b = temp;
        }

        while (b != 0)
        {
            if ((b & 01) != 0) 
            {
                result = add(result, a);
            }
            a <<= 1;
            b >>= 1;
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
        if (theList == null)
        {
             theList = new EquationList(equation, result, null);
        }
        else
        {
            theList = new EquationList(equation, result, theList);
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
        EquationList walker = null;
        walker = theList;
        while (walker != null)
        {
            System.out.println(walker.equation+ " = " + walker.result);
            walker = walker.next;
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
        int i = 0;
        EquationList walker = theList;
        while((i < n) && (walker != null))
        {
            i+=1;
            System.out.println(walker.equation + " = " + walker.result);
            walker = walker.next;
        }
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (theList != null){
        theList = theList.next;
        }
        
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        while(theList != null)
        {
            theList = theList.next;
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
        EquationList walker = theList;
        int sum = 0;
        while (walker != null)
        {
            sum += walker.result;
            walker = walker.next;
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
        EquationList walker = theList;
        int product = 1;
        while (walker != null)
        {
            product *= walker.result;
            walker = walker.next;
        }
        return product;
    }
}