import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList history = null;
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int carry = x & y;
        int sum = x ^ y;
        while(carry != 0)
        {
            int shift = carry << 1;
            carry = sum & shift;
            sum = sum ^ shift;
        }
        return sum;
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
        int product = 0;
        if (x > 0) {
            for (int i = 0; i<x; i++) {
                product = add(product,y);
            }
        }
        if (x < 0) {
            x = add((~x),1);
            for (int i = 0; i<x; i++) {
                product = add(product,y);
            }
            product = add((~product),1);
        }
        if (x == 0) {
            product = 0;
        }
    
        return product;
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
        EquationList saved = new EquationList(equation,result,history);
        history = saved;
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
        printAllHistory(history);
        

    }
    
    public void printAllHistory(EquationList list) {
        if (list != null) {
            System.out.println(list.equation +  " = " + list.result);
            printAllHistory(list.next);
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
        printHistory(history,n);
    } 
    public void printHistory(EquationList list, int n) {
        if (n == 0) {
        }
        for (int i=n; i>0;) {
            System.out.println(list.equation + " = " + list.result);
            list = list.next;
            i = i - 1; 
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
        int cumulativeSum = cumulativeSum(history);
        return cumulativeSum;
    }
    public int cumulativeSum(EquationList listtosum) {
        int index = 0;
        int cumulativeSum = 0;
        while (listtosum != null) {
            cumulativeSum = add(cumulativeSum,listtosum.result);
            listtosum = listtosum.next;
            index = index + 1;
        }
        return cumulativeSum;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        int cumulativeProduct = cumulativeProduct(history);
        return cumulativeProduct;
    }
    public int cumulativeProduct(EquationList xlist) {
        int cumulativeProduct = 1;
        while (xlist != null) {
            cumulativeProduct = multiply(cumulativeProduct,xlist.result);
            xlist = xlist.next;
        }
        return cumulativeProduct;
    }
}