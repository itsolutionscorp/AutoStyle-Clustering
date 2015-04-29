import list.EquationList;

public class Calculator {
    EquationList history = null;
    int equationCounter = 0;
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
        if(y == 0) {
            return x;
        }

        if(x == 0) {
            return y;
        }

        int xor = (x ^ y);
        int and = ((x & y) << 1);

        return add(xor, and);
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

        int counter = 0;
        int sum = 0;
        int tempX = Math.abs(x);
        int tempY = Math.abs(y);
        
        while (counter != tempX) {
            sum = add(sum,tempY);
            counter = add(counter, 1);
        }

        if((Math.signum(x) == -1.0) ^ (Math.signum(y) == -1.0)) {
            sum = ~sum;
            sum = add(sum,1);
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
        if (history == null){
            history = new EquationList(equation, result, null);
            equationCounter = 1;
        } else {
            history = new EquationList(equation, result, history);
            equationCounter += 1;
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
        printHistory(equationCounter);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        if (n > equationCounter) {
            printAllHistory();
        } else {
            EquationList temp = history;
            while(n != 0) {
                System.out.println(temp.equation + " = " + temp.result);
                n -= 1;
                temp = temp.next;
            }
        }
    }     

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (history != null) {
            history = history.next;
            equationCounter -= 1;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        history = null;
        equationCounter = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        if (history == null) {
            return sum;
        } else {
            EquationList temp = history;
            while(temp != null) {
                sum += temp.result;
                temp = temp.next;
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
        int prod = 1;
        if (history == null) {
            return prod;
        } else {
            EquationList temp = history;
            while(temp != null) {
                prod *= temp.result;
                temp = temp.next;
            }
            return prod;
        }
    }
}