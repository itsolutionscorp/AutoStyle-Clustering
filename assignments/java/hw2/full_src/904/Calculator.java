import list.EquationList;

public class Calculator {
    public EquationList A = null;
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
        // YOUR CODE HERE
        int a = x; int b = y;
        int xor, and, temp;
        and = a & b;
        xor = a ^ b;

        while (and != 0) {
            and <<= 1;
            temp = xor ^ and;
            and &= xor;
            xor = temp;
        }
        
        return xor;
    }
// After a few hours of deep thought reaching no avail, help was received from the supreme overlord StackOverflow herself
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
        int a = x; int b = y; int result = 0;
        while (b!= 0){
            if ((b & 01) != 0){
                result = add(result,a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return result;
    }

    // after more hours of deep thought, even more help was received from the magician StackOverflow
        
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
        A = new EquationList(equation, result, A);

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

        EquationList C = A;
        if (C == null){
        
        }
        while (C != null){
            if (C == null){
            break;
        }
            System.out.println(C.equation + " = " + C.result);
       
            C = C.next;
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
        int counter = 0;
        EquationList B = A;
        if (B == null){
            
        }
        
        while (counter < n){

            if (B == null){
            break;
        }
            System.out.println(B.equation + " = " + B.result);
            
            B = B.next;
            counter += 1;
        }
        // YOUR CODE HERE
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        if (A == null) {
            A = null;
        }
        else {
            A = A.next;
        }
        
        // YOUR CODE HERE
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        A = null;
        // YOUR CODE HERE
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int totalSum = 0;
        EquationList E = A;
        while (E != null){
        totalSum = totalSum + E.result;
        E = E.next;
        }
        return totalSum;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        // YOUR CODE HERE
        EquationList G = A;
        int totalProduct = 1;
        while (G != null){
        totalProduct = totalProduct * G.result;
        G = G.next;
        }
        return totalProduct;
    }
}