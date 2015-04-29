import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equationHistory = null;

    /**
     * TASK 2: ADDING WITH BIT OPERATIONS
     * add() is a method which computes the sum of two integers x and y using 
     * only bitwise operators.
     * @param x is an integer which is one of two addends
     * @param y is an integer which is the other of the two addends
     * @return the sum of x and y
     **/

    public int add(int x, int y){
        while (y != 0){
            int carried = (x & y); //this saves the digit that is carried because in the next step the xor method will "delete" that digit
            x = x ^ y; //this adds the digits together but if there is a missing number due to the inability to carry, it is saved in the variable "carried"
            y = carried << 1; //this takes the saved variable carried and rebinds it to the second digit, in this case y because it is not used
            // the y variable will eventually reach zero because after the binary is shifted so far left it will negate to zero
        } //so the series of steps listed above will keep track of the variable that cannot be added due to the inability to add numbers together
        //afterwards, the step where x^y, the blank zero will be saved after carried is reassigned to x^y
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
    public int multiply(int x, int y) {
        int memory = 0;
        if (x > 0 && y > 0){
            while(y != 0){
                memory = (add(memory,x));
                y = y - 1;
            }
        }

        if (x < 0 && y > 0){ // case if first number is negative
            x = Math.abs(x);
            while(y != 0){
                memory = (add(memory,x));
                y = y - 1;
            }
            add(memory, -1);
            memory = ~(add(memory, -1));

        }

        if (x > 0 && y < 0){ // case if second number is negative
            y = Math.abs(y);
            while(y != 0){
                memory = (add(memory,x));
                y = y - 1;
            }
            add(memory, -1);
            memory = ~(add(memory, -1));
        }

        if (x < 0 && y < 0){ // case if both numbers are negative
            x = Math.abs(x);
            y = Math.abs(y);
            while(y != 0){
                memory = (add(memory,x));
                y = y - 1;
            }
        }
        return memory;
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
        equationHistory = new EquationList(equation, result, equationHistory);
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printAllHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  Please print in 
     * the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printAllHistory() {
        EquationList tempEquations = equationHistory;
        int count = 0;
        for (int i = 0; tempEquations != null; i++){
            tempEquations = tempEquations.next;
            count++;
        }
        printHistory(count);
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
        EquationList tempEquations = equationHistory;
        for( int i = 0; i < n; i++){
            System.out.println(tempEquations.equation + " = " + tempEquations.result);
            tempEquations = tempEquations.next;
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        equationHistory = equationHistory.next;
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
        equationHistory = null;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int resultQuantity(EquationList equationlistA) {
            int count = 0;
            for (int i = 0; equationlistA != null; i++){
            equationlistA = equationlistA.next;
            count++;
        }
        return count;
    }

    public int cumulativeSum() {
        // YOUR CODE HERE
        EquationList tempEquations = equationHistory;
        int equationLength = resultQuantity(tempEquations);
        int count = 0;
        int total = 0;
        if (equationLength == 0){
            return 0;
        }
        for(int i=0; i < equationLength; i++){
            total = (add(total, tempEquations.result));
            tempEquations = tempEquations.next;
            count++;
        }
        return total;
    }


    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeProduct() computes the product over the result of each equation 
     * in history.
     * @return the product of all of the results in history
     **/
    public int cumulativeProduct() {
        EquationList tempEquations = equationHistory;
        int equationLength = resultQuantity(tempEquations);
        int count = 0;
        int total = 1;
        if (equationLength == 0){
            return 1;
        }
        for(int i=0; i < equationLength; i++){
            total = (multiply(total, tempEquations.result));
            tempEquations = tempEquations.next;
            count++;
        }
        return total;
    }
}