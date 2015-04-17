import list.EquationList;

public class Calculator {
    EquationList listOfEquations;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int mask = 1;
        int carry = 0;
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum = sum | ((x & mask) ^ (y & mask) ^ (carry << 1));
            carry = ((carry << 1)&(x & mask))|((carry << 1)&(y & mask))|((x & mask)&(y & mask));
            mask = mask << 1;
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
        int result = 0;
        int mask = 1;
        boolean xIsNegative = false;
        if (x < 0){
            x = add(~x,1);
            xIsNegative = true;
        }
        for (int i = 0; i < 32; i++) {
            if ((mask & x) >> i == 1){
                result += y << i;
            }
            mask = mask << 1;
        }
        if(xIsNegative) {
            result = add(~result,1);
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
        EquationList pointer;
        if (listOfEquations == null) {
            listOfEquations = new EquationList(equation,result,null);
            return;
        }
        pointer = listOfEquations;
        while (pointer.next!=null) {
            pointer = pointer.next;
        }
        pointer.next = new EquationList(equation,result,null);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        int counter = 0;
        EquationList pointer = listOfEquations;
        while (pointer != null){
            counter++;
            pointer = pointer.next;
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
        if (n == 0) {
            return;
        }
        printHistoryR(n,listOfEquations);
    }

    private int printHistoryR (int n,EquationList list) {
        int progress = 0;
        if (list == null){
            return 0;
        }
        progress = 1 + printHistoryR(n,list.next);
        if (progress <= n){
            System.out.println(list.equation + " = " + list.result);
            return progress;
        }
        return progress;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList pointer;
        pointer = listOfEquations;
        if (pointer == null){
            return;
        }
        if (pointer.next == null) {
            listOfEquations = null;
        }
        while (pointer.next.next != null) {
            pointer = pointer.next;
        }
        pointer.next = null;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        listOfEquations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList pointer = listOfEquations;
        int result = 0;
        if (pointer == null) {
            return 0;
        }
        while (pointer != null) {
            result += pointer.result;
            pointer = pointer.next;
        }
        return result;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList pointer = listOfEquations;
        int result = 1;
        if (pointer == null) {
            return 1;
        }
        while (pointer != null) {
            result *= pointer.result;
            pointer = pointer.next;
        }
        return result;
    }
}