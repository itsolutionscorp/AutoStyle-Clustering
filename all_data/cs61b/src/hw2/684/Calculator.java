import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList head = null;
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
        int bitA = x, bitB = y, xor, and, temp;
        and = bitA & bitB;
        xor = bitA ^ bitB; 
        if(x == 0){ return y; }
        if(y == 0){ return x; }

        while (and != 0)
        {
            and <<= 1;
            temp = xor^and;
            and &= xor;
            xor = temp;
        }
        return xor;
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
        // YOUR CODE HERE
        if(x ==0 || y==0){return 0;}
        int bitA = x, bitB = y, result =0;
        while (bitB != 0)
        {
            if((bitB & 1) != 0){
                result += bitA;
            }
            bitA <<= 1;
            bitB >>= 1;
            // System.out.println("A: "+ bitA + "  B: " + bitB + "  Added: "+ result);
        }
        // return result;
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
        // YOUR CODE HERE
        EquationList temp = new EquationList(equation, result, null);
        EquationList current = head;
        temp.next = current;
        head = temp;
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
        EquationList tempEq = head;
        while(tempEq != null){
            System.out.println(tempEq.equation + " = " + tempEq.result);
            tempEq = tempEq.next;
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
        // YOUR CODE HERE
        EquationList tempEq = head;
        while(n != 0 && tempEq !=null){
            if(tempEq.next != null){
                System.out.println(tempEq.equation + " = " + tempEq.result);
                tempEq = tempEq.next;
            }
            n--;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList temp = head;
        head = temp.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        head = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum =0;
        if(head != null){
            EquationList temp = head;
            while (temp != null){
                sum += temp.result;
                temp = temp.next;
            }
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
        // YOUR CODE HERE
        int product = 1;
        if(head != null){
            EquationList temp = head;
            while (temp != null){
                product *= temp.result;
                temp = temp.next;
            }
        }
        return product;
    }
}