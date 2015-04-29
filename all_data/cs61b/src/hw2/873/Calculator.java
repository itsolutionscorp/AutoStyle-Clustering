import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList calcstore = new EquationLst("initial", 0 , null); 

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) { 
        while (y != 0){
            int carry = x & y;

            x = x ^ y; 

            y = carry << 1;
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
    public int getBit(int a, int i) {
            return (a >> i) & 1;
        }

    public int multiply(int x, int y) {

       int store = 0;
       while (y != 0){
        if (getBit(y,0) == 1){
            store = add(store, x);
            y = y >> 1;
            x = x << 1;
        }
        y = y >> 1;
        x = x << 1;
    }
    return store;

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
        calcstore = new EquationList(equation, result, calcstore);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        while (calcstore.equation != "initial") {
            printHistory(count);
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
        int count = 0; 
        while ((calcstore.next != "initial")) {
            count = count + 1;
            calcstore = calcstore.next;
        }
        System.out.println(calcstore.equation + "=");
        System.out.println(calcstore.result);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        calcstore=calcstore.next;
        }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        calcstore = new EquationList("initial", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int csum = 0;
        EquationList loop = calcstore;
        if (loop.equation == "initial"); {
            return -1;
        }
        while (loop.equation != "initial");{
        csum = csum + loop.result;
        loop = loop.next;
        }
        return csum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        int cprdt = 0;
        EquationList loop = calcstore;
        if (loop.equation == "initial"); {
            return -1;
        }
        while (loop.equation != "initial");{
        cprdt = cprdt * loop.result;
        loop = loop.next;
        }
        return cprdts;
        return -1;
    }
}