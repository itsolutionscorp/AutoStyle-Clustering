import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    private EquationList previousEquations = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/
    public int add(int x, int y) {

        // // YOUR CODE HERE
        int sum = 0;
        int carry = 0;
        for (int i = 0; i < 32; i++){
            if (((getBit(x, i) ^ getBit(y, i)) == 1 && carry == 0) ||
                ((getBit(x, i) | getBit(y, i)) == 0 && carry == 1) ){
                sum = setBit(sum, i);
                carry = 0;
            }
            else if ((getBit(x, i) | getBit(y, i)) == 0 && carry == 0){
                carry = 0;
            }
            else if (((getBit(x, i) & getBit(y, i)) == 1 && carry == 0) ||
                     ((getBit(x, i) ^ getBit(y, i)) == 1 && carry == 1)){
                carry = 1;
            }
            else{
                sum = setBit(sum, i);
                carry = 1;
            }
        }
        return sum;
    }

    private int getBit(int x, int i){
        x = x & (1 << i);
        return x >>> i;
    }

    private int setBit(int x, int i){
        return (x ^ (1 << i));
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
        int sum = 0;
        //twos complement
        if (x < 0 && y < 0){
            x = add(~x, 1);
            y = add(~y, 1);
        }
        // make y the larger number
        if (y < x){
            int temp = y;
            y = x;
            x = temp;
        }    
        for (int i = 0; i < y; i++){
            sum = add(sum, x);
        }
        return sum;
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
        previousEquations = new EquationList(equation, result, previousEquations);
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
        EquationList curr = previousEquations;
        int i = 0;
        while(curr != null){
            curr = curr.next;
            i += 1;
        }
        printHistory(i);
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
        EquationList curr = previousEquations;
        while (i < n && curr != null){
            System.out.println(curr.equation + " = " + curr.result);
            curr = curr.next;
            i += 1;
        }

    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (previousEquations != null){
            previousEquations = previousEquations.next;
        }
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        previousEquations = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        // YOUR CODE HERE
        if (previousEquations == null){
            return 0;
        }
        EquationList curr = previousEquations;
        int sum = 0;
        while (curr != null){
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
         if (previousEquations == null){
            return 1;
        }
        EquationList curr = previousEquations;
        int product = 1;
        while (curr != null){
            product *= curr.result;
            curr = curr.next;
        }
        return product;
    }
}