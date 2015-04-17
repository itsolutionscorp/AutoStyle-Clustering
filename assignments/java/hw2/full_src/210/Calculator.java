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
        for (int i= 0; i <32; i++){
            if ((((x >> i) & 1) == 1) && (((y >> i) & 1) == 1)) {//both 1
                int pointer= i;
                pointer++;
                while (((x >> pointer) & 1) == 1){//if there is carry
                        x= x ^ (1 << pointer); //change next index to 0
                        pointer++;
                    }
                x= x | (1<< pointer);
                x= x ^ (1<< i);
            }
            else if ((((x >> i) & 1) != 1) && (((y >> i) & 1) == 1)) {
                x= x | (1 << i);
            }   
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
    public int multiply(int x, int y) {
        int result= x;
        for (int i= 1; i < y ; i++){
            result = add(result, x);
        }
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
    EquationList history;
    public void saveEquation(String equation, int result) {
        if (history == null){
             history = new EquationList(equation, result, null);
        } else {
            history = new EquationList(equation, result, history);
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
        EquationList temp  = history;
        while (history != null){
            printHistory(history.result);
            history = history.next;
        }
        history = temp;
    }

    /**
     * TASK 5B: CALCULATOR HISTORY - PRINT HISTORY HELPER METHODS
     * printHistory() prints each equation (and its corresponding result), 
     * most recent equation first with one equation per line.  A maximum of n 
     * equations should be printed out.  Please print in the following format:
     * Ex   "1 + 2 = 3"
     **/
    public void printHistory(int n) {
        System.out.println(history.equation + " = " + n);
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        if (history != null){
            history = history.next;
        }  
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        // YOUR CODE HERE
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
        int sum = 0;
        EquationList p = history;
        while (history != null){
            sum = sum + history.result;
            history = history.next;
        }
        history = p;
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
        int prod = 1;
        EquationList p = history;
        while (history != null){
            prod = prod * history.result;
            history = history.next;
        }
        history = p;
        return prod;
    }
}