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

    // public Calculator() {
        EquationList history;
        int historyLength = 0;
    //}

    public int add(int x, int y) {
        // YOUR CODE HERE
        int kindaAdd = x ^ y; //"Adds" two digits. 0 and 1 become 1. 00 or 11 become 0. 1+1 dealt with next.
        int mustCarryTheOne = (x & y) << 1; //Determines when we need to carry a 1 and
                                            //shifts these ones leftward, to tne appropriate place 
        
        if (mustCarryTheOne != 0) {
            return add(kindaAdd,mustCarryTheOne);
        }
        return kindaAdd;
    } //Comments: Function is recursive, but uses only bitwise operators.

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
        // METHOD 1: Use a loop. Not recursive.
        /*
        int sum = 0;
        for(int i = 0; i < 32; i++){
            int yShifted = y >>> i; //Shift the (binary) digits of y to determine identity of each digit.
            int shiftedLastDigit = yShifted & 1; //1 if digit is 1; 0 otherwise.
            if (shiftedLastDigit == 1){
                int valueToAdd = x << i; //Add the appropriate multiple of x based on placement of 1. 
                sum = add(sum, valueToAdd); 
            }
        }
        return sum;
        */
        // METHOD 2: Use recursion.

        if (y == 0) {return 0;}
        else if ((y & 1) == 0) {
            return multiply(x, y>>>1) << 1; //If the last digit is 0, shift y to the right,
                                            //multiply this by x, then shift answer back.
        }
        else {
            return add(x, multiply(x, y>>>1) << 1); //If last digit is 1, do above, then add x.
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
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE
        if (history == null) {
            history = new EquationList(equation, result, null);
            historyLength = 1;
        }
        else {history = new EquationList(equation, result, this.history);
            historyLength++;
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
        printHistory(historyLength+1);
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
        EquationList historyPointer = history;
        if (historyPointer == null) {return;}
        else {
            while(historyPointer != null && i < n) {
                System.out.println(historyPointer.equation + " = " + historyPointer.result);
                historyPointer = historyPointer.next;
                i++;
            }
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
        // YOUR CODE HERE
        EquationList histPointer = history;
        int sum = 0;
        if (histPointer == null) {return sum;}
        else {
            while (histPointer != null) {
                sum += histPointer.result;
                histPointer = histPointer.next;
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
        // YOUR CODE HERE
        EquationList histPointer = history;
        int product = 1;
        if (histPointer == null) {return product;}
        else {
            while (histPointer != null) {
                product *= histPointer.result;
                histPointer = histPointer.next;
            }
            return product;
        }
    }
}