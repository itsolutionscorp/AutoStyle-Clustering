import list.EquationList;
//Adding this particular comment just to get Git to work
public class Calculator {
    EquationList history;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        while(y!=0){
            int nicelyAdded = x^y;
            int onePlusOnes = x & y;
            int carryOvers = onePlusOnes << 1;
            x = nicelyAdded;
            y = carryOvers;
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
        int collector = 0;
        while (x!=0){
            int lastBit = 1 & y;
            if(lastBit == 1){
                collector = add(collector, x);
            }
            y = y >> 1;
            x = x << 1;
        }
        return collector;
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
        if (history == null)
            history = new EquationList(equation, result, null);
        else
            history = new EquationList(equation, result, history);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistoryHelper(EquationList pointer) {
        if (pointer != null){
            printAllHistoryHelper(pointer.next); //should work
            System.out.println(pointer.equation + " = " + pointer.result);
        }
    }

    public void printAllHistory() {
        printAllHistoryHelper(history);
    }
    

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistoryHelper(EquationList pointer, int num) {
        if (pointer != null) {
            if (num<2)
                System.out.println(pointer.equation + " = " + pointer.result);
            else {
                System.out.println(pointer.equation + " = " + pointer.result);
                printHistoryHelper(pointer.next, num-1);
            }
        }
    }

    public void printHistory(int n) {
        printHistoryHelper(history, n);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void clearHistory() {
        history = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void undoEquation() {
       	history = history.next;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int collector = 0;
        EquationList pointer = history;
        while (pointer != null) {
            collector += pointer.result;
        }
        return collector;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int collector = 1;
        EquationList pointer = history;
        while (pointer != null) {
            collector *= pointer.result;
        }
        return collector;
    }
}