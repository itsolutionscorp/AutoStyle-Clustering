import list.EquationList;


public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    EquationList equations;

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
        int num = 0;
        int carryNum = 0;
        int stayNum = 0;
        int counter = 0;

        while ((carryNum == 1 || x != 0 || y != 0) && counter < 32) {
            int changeX = x & 1;
            int changeY = y & 1;
            stayNum = stay(carryNum, changeX, changeY);

            carryNum = carry(carryNum, changeX, changeY);

            num = (stayNum << counter) | num;

            x = x >>> 1;

            y = y >>> 1;

            counter += 1;

        }

        return num;

    }

    private int stay(int a, int b, int c) {
        return a ^ b ^ c;
    }

    private int carry(int d, int e, int f) {
        return d & e | e & f | d & f;
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
        int counter = 0;
        int newNum = 0;
        int columnY = y;
        int columnX = x;
        int finalNum = 0;

        while (y != 0) {
            columnY = y & 1;

            if (columnY == 0) {
                newNum = 0;
            }

            else {
                newNum = x;
            }

            newNum = newNum << counter;
            y = y >>> 1;
            counter += 1;
            columnX = x;

            finalNum = add(finalNum, newNum);

        }

        return finalNum;
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

        if (equations == null) {
            equations = new EquationList(equation, result, null);
        }

        else {

            equations = new EquationList(equation, result, equations);
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
        EquationList pointer = equations;
        while (pointer != null) {
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
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
        // YOUR CODE HERE
        EquationList pointer = equations;
        while (n > 0) {
            pointer = pointer.next;
            System.out.println(pointer.equation + " = " + Integer.toString(pointer.result));
            n = n - 1;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equations = equations.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList pointer = equations;
        int sum = 0;
        while (pointer != null) {
            sum = add(sum, pointer.result);
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
        // YOUR CODE HERE
        EquationList pointer = equations;
        int product = 1;
        while (pointer != null) {
            product = multiply(product, pointer.result);
            pointer = pointer.next;
        }

        return product;
    }
}
