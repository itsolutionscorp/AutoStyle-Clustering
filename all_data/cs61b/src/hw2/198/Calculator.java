import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList eList = null; // initialize a global variable
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int and, xor, temp;
        and = x & y; // check if you need to carry over
        xor = x ^ y; // will sum up x and y (without any shift)
        while (and != 0) { // there is a need to shift (you need to carry)
            and <<= 1; // left shift by one
            temp = xor ^ and; // temporarily hold a possible solution
            and = and & xor; // update AND to see it is still necessary to carry
            xor = temp; // update XOR as a possible solution
        }
    return xor; // return answer
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
        /*
        * I want to find some way to loop
        * where I keep adding one of the terms
        * as an alternative for a direct multiplication
        * this should serve to be simpler
        */
        int and, returnVal; // declare local variables
        returnVal = 0; // set equal to zero
        while (x != 0){ // x will be shifted inwards
            and = x & 1; // this "trick" serves as a checkpoint to see if its time to add
            if (and != 0) {
                returnVal = add(returnVal,y); // cumulate
            }
        x >>>= 1; /* logical shift x inwards (not completely sure about why this works)
                    * but it helps calculate negatives */
        y <<= 1; // arithmetic shift y outwards
        }
    return returnVal;
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
        eList = new EquationList(equation, result, eList); // creates a new EquationList each call with the previous stored as next
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result),
     * most recent equation first with one equation per line.  Please print in
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList temp = eList; // put our EquationList into a temporary variable
        while (temp != null){ // while there is still some on the list
            System.out.println(temp.equation + " = " + temp.result); // print out result
            temp = temp.next; // move on to the next result
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
        EquationList temp = eList;
        out: // label outer loop
            for (int i=0; i < n; i++){
                if (temp == null){ // something to prevent the program from crashing
                    break out; // if you call history 2 and only one equation is saved, it won't crash the program
                }
                System.out.println(temp.equation + " = " + temp.result); // print out results
                temp = temp.next; // go to the next equation

            }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        eList = eList.next; // forgets most recent entry
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eList = null; // completely reset our EquationList
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        EquationList temp = eList; // save to temporary variable
        int sum = 0; // default to 0
        while (temp != null){ // as long as there are still equations in the history
            sum += temp.result; // cumulate the sum
            temp = temp.next; // move on to the next result
        }

        return sum; // return cumulative sum
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList temp = eList; // save to temporary variable
        int product = 1; // set default to 1
        while (temp != null){ // while there is still a history
            product *= temp.result; // multiply results
            temp = temp.next; // move on to next equation
        }
        return product; // return cumulative results
    }
}