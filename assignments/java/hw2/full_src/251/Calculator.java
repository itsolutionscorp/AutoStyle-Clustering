import list.EquationList;

public class Calculator {
    // YOU MAY WISH TO ADD SOME FIELDS
    public EquationList equHistory;
    public int historySize;

    public Calculator(){
        equHistory = null;
        historySize = 0;
    }

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
        int temp1=x, temp2=y, temp3=0;

        while(temp2!=0){

            temp3 = temp1 & temp2;  //calculate the carryover
            temp1 = temp1 ^ temp2;  //sum them up
            temp2 = temp3 << 1;     //shift the carryover for the next summing

        }
        return temp1;

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
        int temp1 = x, temp2 = y, result = 0;

        while(temp2 != 0){

            if ((temp2 & 1) != 0)
                result = add(result, temp1);

            temp1 = temp1 << 1;
            temp2 = temp2 >>> 1;

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
    public void saveEquation(String equation, int result) {
        // YOUR CODE HERE

        //check if the string is a valid equation
        if(equation.contains(" + ") || equation.contains(" * ")){
            if((equation.charAt(0) == '-') || Character.isDigit(equation.charAt(0))){
                //add the new equation to equHistory
                EquationList newEqu = new EquationList(equation, result, null);
                newEqu.next = equHistory;
                equHistory = newEqu;
                historySize++;
            }
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
        printHistory(historySize);
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
        if ((0 < n) & (n <= historySize)){
            EquationList ptr = equHistory;

            for(int i=0; i<n; i++){
                System.out.println(ptr.equation + " = " + Integer.toString(ptr.result));
                ptr = ptr.next;
            }
        }
    }    

    /**
     * TASK 6: CLEAR AND UNDO
     * undoEquation() removes the most recent equation we saved to our history.
    **/
    public void undoEquation() {
        // YOUR CODE HERE
        EquationList deleteThis = equHistory;

        if (historySize > 0){
            equHistory = equHistory.next;
            historySize--;
        } 
    }

    /**
     * TASK 6: CLEAR AND UNDO
     * clearHistory() removes all entries in our history.
     **/
    public void clearHistory() {
        equHistory = null;
        historySize = 0;
    }

    /**
     * TASK 7: ADVANCED CALCULATOR HISTORY COMMANDS
     * cumulativeSum() computes the sum over the result of each equation in our 
     * history.
     * @return the sum of all of the results in history
     **/
    public int cumulativeSum() {
        int sum = 0;

        if(historySize > 0){
            EquationList ptr = equHistory;
            
            for(int i=0; i<historySize; i++){
                sum += ptr.result;
                ptr = ptr.next;
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
        int product = 1;

        if(historySize > 0){
            EquationList ptr = equHistory;

            for(int i=0; i<historySize; i++){
                product *= ptr.result;
                ptr = ptr.next;
            }
        }
        return product;
    }
}