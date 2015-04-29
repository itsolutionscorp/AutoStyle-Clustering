import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList eqList = new EquationList("0", 0, null); 

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        int flip = x ^ y;
        int mask = x & y;
        if (mask << 1 == 0){
            return flip;
        }
        return add(flip, mask << 1);
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
        if (y == 0) {
            return 0;
        }
        int recurse = (multiply(x, (y>>>1)) << 1);
        if ((y & 1) == 1){
            return add(x, recurse);
        }
        return recurse;  
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
        EquationList copy = eqList;
        while (copy.next != null){
            copy = copy.next;
        }
        copy.equation = equation;
        copy.result = result;
        copy.next = new EquationList("0", 0, null);
        
        
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        if (eqList.equation == "0"){
            System.out.print("");
        }
        EquationList copy = eqList;
        int counter = 0;
        while (copy.next != null){
            counter += 1;
            copy = copy.next;
        }
        while (counter > 0){
            copy = eqList;
            for (int i = 1; i < counter; i++){
                copy = copy.next;
            }
            System.out.println(copy.equation + " = " + copy.result);
            counter = counter - 1;
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
        if (eqList.equation == "0"){
            System.out.print("");
        }
        EquationList copy = eqList;
        int counter = 0;
        int numCounter = 0;
        while (copy.next != null){
            counter += 1;
            copy = copy.next;
        }
        while (counter > 0 && numCounter<n){
            copy = eqList;
            for (int i = 1; i < counter; i++){
                copy = copy.next;
            }
            System.out.println(copy.equation + " = " + copy.result);
            counter = counter - 1;
            numCounter += 1;
        }
    } 

    private EquationList getLast(EquationList eq){
        if (eq.next.next.next == null){
            return eq;
        }
        return getLast(eq.next);
    }
    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        EquationList copy = eqList;
        if (copy.next.equation == "0") clearHistory();
        else {
            while (copy != null){
                if (copy.next == null){ 
                    getLast(eqList).next = new EquationList("0", 0, null);
                    break;
                    }
                copy = copy.next;
                }
            }
        }
    

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = new EquationList("0", 0, null);
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;
        EquationList copy = eqList;
        while (copy.next != null){
            sum += copy.result;
            copy = copy.next;
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
        int prod = 1;
        EquationList copy = eqList;
        while (copy.next != null){
            prod = prod*copy.result;
            copy = copy.next;
        }
        return prod;
    }
}