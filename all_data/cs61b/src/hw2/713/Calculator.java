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
    public EquationList list = null;

    public int add(int x, int y) {
        // YOUR CODE HERE

        while (y != 0){
            int a = x ^ y;
            int b = ((x & y) << 1);
            x = a;
            y = b;
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
    public int multiply(int x, int y){
        // YOUR CODE HERE
        int currentsum = 0;

        if (y < 0){
            x = add(~x, 1); 
            y = add(~y, 1); 
        }
        while (y > 0){
            int a = x;
            int b = y;
            int x_or = 1; 

            while (b > 1){
                a = a << 1;
                b = b >>> 1;
                x_or = x_or << 1;
            }

            currentsum = add(currentsum, a);
            y = y ^ x_or;

        }
        return currentsum;

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
        list = new EquationList(equation, result, list);
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
        EquationList temp = list;
        int number_equations = 0;
        while (temp != null){
            number_equations = number_equations + 1;
            temp = temp.next;
        }
        
        printHistory(number_equations);
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
        EquationList temp = list;

        while (n > 0 & temp != null){
            System.out.println(temp.equation + " = " + Integer.toString(temp.result));
            temp = temp.next;
            n = n - 1;
        }
        
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        list = list.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        list = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (list == null){
            return 0;
        }

        EquationList temp = list;
        int sum = 0;
        while (temp != null){
            sum = sum + temp.result;
            temp = temp.next;
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
        if (list == null){
            return 1;
        }

        EquationList temp = list;
        int product = 1;
        while (temp != null){
            product = product * temp.result;
            temp = temp.next;
        }
        return product;
    }
}