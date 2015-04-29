import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList eqList; // recent -> oldest
    private boolean begin = true; // first equation, used in save and clear
    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {
        // sum bits and carry bits are obtained using xor, and
        if(y == 0){
            return x;
        }
        int addedBits = x ^ y;
        int carry = (x & y) << 1;
        return add(addedBits, carry);
    }

    /**
     * TASK 3: MULTIPLYING WITH BIT OPERATIONS
     * multiply() is a method which computes the product of two integers x and 
     * y using only bitwise operators.
     * @param x is an integer which is one of the two numbers to multiply
     * @param y is an integer which is the other of the two numbers to multiply
     * @return the product of x and y
     **/

    public int multiply(int x, int y){
        // multiplying by 2^n shifts original bits n positions to the left
        if(y == 0){
            return 0;
        }
        if((y & 1) == 1){
            return add(x, multiply(x, y ^ 1));
        }
        return multiply(x << 1, y >>> 1);
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

    public void saveEquation(String equation, int result){
        if(begin){
            eqList = new EquationList(equation, result, null);
            begin = false;
        }
        else{
            eqList = new EquationList(equation, result, eqList);
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
        int length = 0;
        EquationList curr = eqList;
        while(curr != null){
            length ++;
            curr = curr.next;
        }
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
        EquationList curr = eqList;
        while(n > 0 && curr != null){
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
            n--;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/

    public void undoEquation(){
        if(eqList != null){
            eqList = eqList.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        eqList = null;
        begin = true;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        int sum = 0;
        EquationList curr = eqList;
        while(curr != null){
            sum += curr.result;
            curr = curr.next;
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
        EquationList curr = eqList;
        while(curr != null){
            product *= curr.result;
            curr = curr.next;
        }
        return product;
    }
}