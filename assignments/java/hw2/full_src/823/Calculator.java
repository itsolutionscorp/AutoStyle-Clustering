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
    public int add(int x, int y) {

        int carryover = x & y;
        int sumOfTwo = x ^ y;

        while (carryover != 0) {
            carryover = carryover << 1;
            int prevSum = sumOfTwo;
            sumOfTwo = carryover ^ sumOfTwo;
            carryover = carryover & prevSum;
        }
        return sumOfTwo;
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

        int finalResult = 0;
        while (y != 0) {
            if ((y & 1) == 1) {
                finalResult = add(finalResult, x);
            }
            x = x << 1;
            y = y >>> 1;      
        }
        return finalResult;
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

    public EquationList history;

    public void saveEquation(String equation, int result) {
        if (history == null) {
            history = new EquationList(equation, result, null);
        }
        else {
            EquationList pointer;
            pointer = history;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = new EquationList(equation, result, null);
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

        int length = lengthOfHis(history);
        printHistory(length);

    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {

        if (history == null) {}
        else {
            EquationList reverseList;
            reverseList = reverseHistory(history);
            while (n != 0) {
            System.out.println(reverseList.equation + " = " + reverseList.result);
            reverseList = reverseList.next;
            n -= 1;
            }
        }
        
    }    

    public EquationList reverseHistory(EquationList eqList) {
        if (eqList == null) {
            return null;
        }
        EquationList newList = new EquationList(eqList.equation, eqList.result, null);
        eqList = eqList.next;
        while (eqList != null){
            newList = new EquationList(eqList.equation, eqList.result, newList);
            eqList = eqList.next;
        }
        return newList;
    }

    public int lengthOfHis (EquationList eqList) {
        if (eqList == null) {
            return 0;
        }
        else{
            int count = 0;
            while (eqList.next != null) {
            count += 1;
            eqList = eqList.next;
            }
            return count + 1;
        }
    }
    


    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {

        EquationList pointer;
        pointer = history;
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

        history = null;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {

        int sumOfAll = 0;
        EquationList pointer = history;
        while (pointer != null) {
            sumOfAll += pointer.result;
            pointer = pointer.next;
        }
        return sumOfAll;

    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {

        if (history == null) {
            return 0;
        }
        else {
            int prodOfAll = 1;
            EquationList pointer = history;
            while (pointer != null) {
                prodOfAll = prodOfAll * pointer.result;
                pointer = pointer.next;
            }
            return prodOfAll;
        }

    }
}

