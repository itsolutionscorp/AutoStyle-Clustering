import list.EquationList;

public class Calculator {
    public EquationList history;
    public int size = 0;
    // YOU MAY WISH TO ADD SOME FIELDS

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int a=x, b=y, c=0;

        while (b!=0){
            c = a & b;
            a = a ^ b;
            b = c << 1;
        }
        return a
        ;
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
        int a=x, b=y, result=0;

        while (b!=0){
            if ((b & 1) == 1) {
                result = add(result, a);
            }
            a = a << 1;
            b = b >>> 1;
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
            // History = new EquationList(equation, result, );
        history = new EquationList(equation, result, history);
        size += 1;    
            // EquationList temp = history;
            // while (temp.next != null) {
            //     temp = temp.next;
            // }temp.next = new EquationList(equation, result, null);
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
        printHistory(size);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3 "
     **/
    public void printHistory(int n) {
        // YOUR CODE HERE
        EquationList temp = history;
        if (temp != null) {
        while (n != 0) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n = n - 1;
        }
        }   
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (size > 0) {
            history = history.next;
            size -= 1;
        }
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
        EquationList temp = history;
        int sum = 0;
        while (temp != null) {
            sum += temp.result;
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
        EquationList temp = history;
        int product = 1;
        while (temp != null) {
            product *= temp.result;
            temp = temp.next;
        }
        return product;
    }
}

