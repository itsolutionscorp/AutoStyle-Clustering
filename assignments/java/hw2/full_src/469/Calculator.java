
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

    //calculator history 
    public EquationList history = null;


    private int majority(int m, int n, int carry) {
        if(m >= 1 && n >= 1 ) {
            return 1;
        } else if(n >= 1 && carry >= 1) {
            return 1;
        } else if(m >= 1 && carry >= 1) {
            return 1;
        } else if (m == 0 && n == 0) {
            return 0;
        } else if(n == 0 && carry == 0 ) {
            return 0;
        } else {
            return 0;
        }
    }

    public int add(int x, int y) {
        int sum = 0;
        int carry = 0;
        for(int i = 0; i < 32; i++) {
            int m = (x & (1 << i));
            int n = (y & (1 << i));
            sum = sum | (carry ^ (m ^ n));
            carry = majority(m, n, carry) << (i + 1);
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
        int sum = 0;   
        for(int i=0; i < 32; i++) {
            int z = (y & (1 << i));
            if(z > 0){
                sum = add(sum, (x << i));
            }
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
        history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/

    public void printAllHistory() {
        EquationList temp = history;
        int counter = 0;
        while(temp != null) {
            counter += 1;
            temp = temp.next;
        }

        printHistory(counter);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        EquationList temp = history;
        while(n != 0) {
            System.out.println(temp.equation + " = " + temp.result);
            temp = temp.next;
            n -= 1;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if(history == null) {
            history = null;
        } else {
            history = history.next;
    } 
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
        EquationList temp = history;
        if(history == null) {
            return 0;
        }
        while(temp != null){
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
        int sum = 1;
        EquationList temp = history;
        if(history == null) {
            return 1;
        }
        while(temp != null) {
            sum *= temp.result;
            temp = temp.next;
            }
        return sum;
    }
}